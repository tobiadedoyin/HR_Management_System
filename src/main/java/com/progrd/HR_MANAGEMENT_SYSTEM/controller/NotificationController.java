package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Notification;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(name = "/notifications/{employeeId}")
    public List<Notification> getNotificationById(@PathVariable long employeeId){
        return notificationService.findByEmployeeId(employeeId);
    }

    @PutMapping(name = "/notifications/{notificationId}")
    public String updateNotificationStatus(@PathVariable int notificationId){
        return notificationService.markAsRead(notificationId);
    }

}
