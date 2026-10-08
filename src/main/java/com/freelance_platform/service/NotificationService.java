package com.freelance_platform.service;

import com.freelance_platform.entity.Notification;
import com.freelance_platform.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotifications(
            Integer userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotifications(
            Integer userId) {

        return notificationRepository
                .findByUserIdAndIsRead(
                        userId,
                        false
                );
    }

    public Notification createNotification(
            Notification notification) {

        return notificationRepository.save(notification);
    }

    public void markAsRead(
            Integer notificationId) {

        Notification notification =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"
                                ));

        notification.setIsRead(true);

        notificationRepository.save(notification);
    }
}