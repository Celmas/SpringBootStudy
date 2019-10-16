package ru.itis.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootstudy.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
