package com.freelance_platform.dto;

import lombok.Data;

@Data
public class SendMessageRequest {

    private Integer projectId;

    private Integer senderId;

    private Integer receiverId;

    private String messageText;

    private String attachmentUrl;
}