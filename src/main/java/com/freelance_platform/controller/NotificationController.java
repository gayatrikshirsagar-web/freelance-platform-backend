package com.freelance_platform.controller;

import com.freelance_platform.entity.Notification;
import com.freelance_platform.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Integer userId) {

        return notificationService
                .getNotifications(userId);
    }

    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(
            @PathVariable Integer userId) {

        return notificationService
                .getUnreadNotifications(userId);
    }

    @PostMapping
    public Notification createNotification(
            @RequestBody Notification notification) {

        return notificationService
                .createNotification(notification);
    }

    @PutMapping("/{notificationId}/read")
    public void markAsRead(
            @PathVariable Integer notificationId) {

        notificationService
                .markAsRead(notificationId);
    }
}