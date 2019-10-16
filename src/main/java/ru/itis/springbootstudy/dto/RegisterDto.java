package ru.itis.springbootstudy.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String login;
    private String email;
    private String password;
}
