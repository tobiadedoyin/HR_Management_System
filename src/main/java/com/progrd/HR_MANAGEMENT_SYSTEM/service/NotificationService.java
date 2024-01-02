package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Notification;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository ntfrpy;
    private final EmployeeRepository employeeRepository;

    public List<Notification> findByEmployeeId(long employeeId){

        return ntfrpy.findNotificationByEmployee_Id(employeeId);
    }

    public Notification saveNotification(long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Notification newNotification = new Notification();
        newNotification.setEmployee(employee);
        newNotification.setMessage("employee "+employee.getFirstName().concat(" "+employee.getLastName())+
                "\nhas been added to the organisation");
        return ntfrpy.save(newNotification);
    }

    public Notification findById(int notificationId){
        return ntfrpy.findById(notificationId).orElseThrow();
    }

    public String markAsRead(int notificationId){
        Notification notification = ntfrpy.findById(notificationId).get();

        notification.setStatus("READ");

        ntfrpy.save(notification);
        return "message marked as read";
    }

}
