package ru.itis.springbootstudy.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootstudy.dto.LoginDto;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
