package com.freelance_platform.controller;

import com.freelance_platform.dto.SendMessageRequest;
import com.freelance_platform.entity.Message;
import com.freelance_platform.service.MessageService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class MessageController {

    private final MessageService messageService;

    public MessageController(
            MessageService messageService) {

        this.messageService = messageService;
    }

    @GetMapping("/project/{projectId}")
    public List<Message> getMessages(
            @PathVariable Integer projectId) {

        return messageService
                .getMessagesByProject(projectId);
    }

    @PostMapping
    public Message sendMessage(
            @RequestBody SendMessageRequest request) {

        return messageService
                .sendMessage(request);
    }
}