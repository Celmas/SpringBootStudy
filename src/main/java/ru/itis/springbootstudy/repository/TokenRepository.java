package ru.itis.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootstudy.model.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findFirstByValue(String value);
}
