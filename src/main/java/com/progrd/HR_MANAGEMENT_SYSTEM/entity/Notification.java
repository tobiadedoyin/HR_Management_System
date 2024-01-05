package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Entity(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private LocalDateTime timestamp;
    private String status;

    @ManyToOne(targetEntity = Employee.class,fetch = FetchType.EAGER)
            @JoinColumn(nullable = false, name = "employee_id")
    Employee employee;

    public Notification(Employee employee) {
        this.message = "message";
        this.timestamp = LocalDateTime.now();
        this.employee = employee;
        this.status = "UNREAD";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
