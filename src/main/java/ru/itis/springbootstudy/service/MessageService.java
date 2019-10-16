package ru.itis.springbootstudy.service;

import ru.itis.springbootstudy.model.Message;

import java.util.Optional;

public interface MessageService {
    Optional<Message> save(Message message);
}
