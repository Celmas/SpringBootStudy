package ru.itis.springbootstudy.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.springbootstudy.model.Message;
import ru.itis.springbootstudy.repository.MessageRepository;

import java.util.Optional;

@Service
public class MessageServiceImpl implements ru.itis.springbootstudy.service.MessageService {
    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> save(Message message) {
        return Optional.of(messageRepository.save(message));
    }
}
