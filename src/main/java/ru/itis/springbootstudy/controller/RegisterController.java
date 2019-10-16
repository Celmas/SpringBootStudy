package ru.itis.springbootstudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootstudy.dto.RegisterDto;
import ru.itis.springbootstudy.dto.TokenDto;
import ru.itis.springbootstudy.service.UserService;

@RestController
public class RegisterController {
    private UserService service;

    public RegisterController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody RegisterDto registerData) {
        return ResponseEntity.ok(service.register(registerData));
    }
}
