package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;
    private LocalDateTime timestamp;
    private String status;

    @ManyToOne(targetEntity = Employee.class,fetch = FetchType.EAGER)
            @JoinColumn(name = "employee_id")
    Employee employee;

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
