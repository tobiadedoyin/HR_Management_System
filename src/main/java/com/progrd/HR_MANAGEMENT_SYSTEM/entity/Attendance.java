package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private double hourWorked;

    private String attendanceStatus;

    public Attendance(Employee employees, LocalDate date, LocalTime signIn, LocalTime signOut,
                      long hourWorked, AttendanceStatus attendanceStatus) {
        this.employees = employees;
        this.date = date;
        this.signIn = signIn;
        this.signOut = signOut;
        this.hourWorked = hourWorked;
        this.attendanceStatus = attendanceStatus.toString();
    }


}
