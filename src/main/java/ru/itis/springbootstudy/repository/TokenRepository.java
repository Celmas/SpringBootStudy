package ru.itis.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootstudy.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
