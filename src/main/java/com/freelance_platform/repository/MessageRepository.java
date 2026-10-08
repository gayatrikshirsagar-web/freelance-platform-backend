package com.freelance_platform.repository;

import com.freelance_platform.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository
        extends JpaRepository<Message, Integer> {

    List<Message> findByProjectIdOrderBySentAtAsc(
            Integer projectId
    );

    List<Message> findByReceiverIdAndReadStatus(
            Integer receiverId,
            Boolean readStatus
    );
}