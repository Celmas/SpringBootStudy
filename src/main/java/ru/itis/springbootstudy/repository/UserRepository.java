package ru.itis.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootstudy.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
