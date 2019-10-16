package ru.itis.springbootstudy.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springbootstudy.dto.LoginDto;
import ru.itis.springbootstudy.dto.RegisterDto;
import ru.itis.springbootstudy.dto.TokenDto;
import ru.itis.springbootstudy.model.Token;
import ru.itis.springbootstudy.model.User;
import ru.itis.springbootstudy.repository.TokenRepository;
import ru.itis.springbootstudy.repository.UserRepository;
import ru.itis.springbootstudy.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final TokenRepository tokenRepository;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    @Value("${token.expired}")
    private Integer expiredSecondsForToken;

    public UserServiceImpl(TokenRepository tokenRepository, PasswordEncoder encoder, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public TokenDto login(LoginDto loginData) {
        Optional<User> userCandidate = userRepository.findByLogin(loginData.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (encoder.matches(loginData.getPassword(), user.getPasswordHash())) {
                String value = UUID.randomUUID().toString();
                Token token = Token.builder()
                        .createdAt(LocalDateTime.now())
                        .expiredDateTime(LocalDateTime.now().plusSeconds(expiredSecondsForToken))
                        .value(value)
                        .user(user)
                        .build();
                tokenRepository.save(token);
                return TokenDto.from(value);
            }
        }
        throw new BadCredentialsException("Incorrect login or password");
    }

    @Override
    public TokenDto register(RegisterDto registerData) {
        User user = User.builder()
                .login(registerData.getLogin())
                .email(registerData.getEmail())
                .passwordHash(encoder.encode(registerData.getPassword()))
                .build();
        userRepository.save(user);
        String value = UUID.randomUUID().toString();
        Token token = Token.builder()
                .createdAt(LocalDateTime.now())
                .expiredDateTime(LocalDateTime.now().plusSeconds(expiredSecondsForToken))
                .value(value)
                .user(user)
                .build();
        tokenRepository.save(token);
        return TokenDto.from(value);
    }

    @Override
    public Optional<User> getUserByTokenValue(String token) {
        Optional<Token> candidate = tokenRepository.findFirstByValue(token);
        User user;
        if (candidate.isPresent()) {
            user = candidate.get().getUser();
            return Optional.of(user);
        }
        throw new IllegalArgumentException("User not found");
    }
}
