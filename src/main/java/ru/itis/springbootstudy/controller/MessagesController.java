package ru.itis.springbootstudy.controller;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootstudy.dto.MessageDto;
import ru.itis.springbootstudy.model.Message;
import ru.itis.springbootstudy.service.MessageService;
import ru.itis.springbootstudy.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MessagesController {
    private final Map<String, List<Message>> messages = new HashMap<>();

    private MessageService messageService;
    private UserService userService;

    public MessagesController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto messageDto) {
        if (!messages.containsKey(messageDto.getTokenValue())) {
            messages.put(messageDto.getTokenValue(), new ArrayList<>());
        }
        for (List<Message> pageMessages : messages.values()) {
            synchronized (pageMessages) {
                Optional<Message> message = messageService.save(Message.builder()
                        .sender(userService.getUserByTokenValue(messageDto.getTokenValue()).get())
                        .time(LocalDateTime.now())
                        .value(messageDto.getText())
                        .build());
                pageMessages.add(message.get());
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesForPage(@RequestParam("token") String token) {
        synchronized (messages.get(token)) {
            if (messages.get(token).isEmpty()) {
                messages.get(token).wait();
            }
            List<Message> response = new ArrayList<>(messages.get(token));
            messages.get(token).clear();
            return ResponseEntity.ok(response);
        }
    }
}
