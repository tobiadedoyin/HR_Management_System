package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employees;
    private LocalDate date;
    private LocalTime signIn;
    private LocalTime signOut;
    private double HourWorked;
    private Status status;

    public Attendance(Employee employees, LocalDate date, LocalTime signIn, LocalTime signOut,
                      long hourWorked, Status status) {
        this.employees = employees;
        this.date = date;
        this.signIn = signIn;
        this.signOut = signOut;
        HourWorked = hourWorked;
        this.status = status;
    }
}
