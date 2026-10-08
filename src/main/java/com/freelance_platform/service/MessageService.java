package com.freelance_platform.service;

import com.freelance_platform.dto.SendMessageRequest;
import com.freelance_platform.entity.Message;
import com.freelance_platform.repository.MessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(
            MessageRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesByProject(
            Integer projectId) {

        return messageRepository
                .findByProjectIdOrderBySentAtAsc(projectId);
    }

    public Message sendMessage(
            SendMessageRequest request) {

        Message message = new Message();

        message.setProjectId(
                request.getProjectId()
        );

        message.setSenderId(
                request.getSenderId()
        );

        message.setReceiverId(
                request.getReceiverId()
        );

        message.setMessageText(
                request.getMessageText()
        );

        message.setAttachmentUrl(
                request.getAttachmentUrl()
        );

        return messageRepository.save(message);
    }
}