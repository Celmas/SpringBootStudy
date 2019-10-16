package ru.itis.springbootstudy.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String tokenValue;
    private String text;
}
