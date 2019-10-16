package ru.itis.springbootstudy.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.springbootstudy.model.Token;
import ru.itis.springbootstudy.repository.TokenRepository;

import java.util.Optional;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private TokenRepository tokensRepository;

    public UserDetailsServiceImpl(TokenRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Token> authenticationCandidate = tokensRepository.findFirstByValue(s);
        if (authenticationCandidate.isPresent()) {
            Token token = authenticationCandidate.get();
            return new UserDetailsImpl(token.getUser(), token);
        }
        return null;
    }
}
