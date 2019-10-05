package ru.itis.springbootstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String value;

    public static TokenDto from(String tokenValue) {
        return TokenDto.builder().value(tokenValue).build();
    }
}
