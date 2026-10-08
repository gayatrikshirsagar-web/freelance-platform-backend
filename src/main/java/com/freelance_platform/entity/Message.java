package com.freelance_platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "sender_id", nullable = false)
    private Integer senderId;

    @Column(name = "receiver_id", nullable = false)
    private Integer receiverId;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "read_status")
    private Boolean readStatus;

    @PrePersist
    protected void onCreate() {

        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }

        if (readStatus == null) {
            readStatus = false;
        }
    }
}