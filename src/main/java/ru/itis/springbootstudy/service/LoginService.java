package ru.itis.springbootstudy.service;

import ru.itis.springbootstudy.dto.LoginDto;
import ru.itis.springbootstudy.dto.TokenDto;

public interface LoginService {
    TokenDto login(LoginDto loginData);
}
