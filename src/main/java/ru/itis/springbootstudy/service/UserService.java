package ru.itis.springbootstudy.service;

import ru.itis.springbootstudy.dto.LoginDto;
import ru.itis.springbootstudy.dto.RegisterDto;
import ru.itis.springbootstudy.dto.TokenDto;
import ru.itis.springbootstudy.model.User;

import java.util.Optional;

public interface UserService {
    TokenDto login(LoginDto loginData);
    TokenDto register(RegisterDto registerData);
    Optional<User> getUserByTokenValue(String token);
}
