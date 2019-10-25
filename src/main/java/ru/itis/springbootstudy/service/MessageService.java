package ru.itis.springbootstudy.service;

import ru.itis.springbootstudy.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> save(Message message);
    List<Message> getAll();
}
