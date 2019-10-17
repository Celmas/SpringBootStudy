package ru.itis.springbootstudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootstudy.dto.LoginDto;
import ru.itis.springbootstudy.dto.TokenDto;
import ru.itis.springbootstudy.service.UserService;

@RestController
public class LoginController {
    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }
}
