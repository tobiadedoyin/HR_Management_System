package com.progrd.HR_MANAGEMENT_SYSTEM.dto;
;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceDto {
    private LocalDate date;

    private String firstName;

    private String lastName;

    private LocalTime signIn;

    private LocalTime signOut;

    private double HourWorked;

    private String attendanceStatus;

    public AttendanceDto(LocalDate date, String firstName, String lastName, LocalTime signIn,
                         LocalTime signOut, double hourWorked, String attendanceStatus) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.signIn = signIn;
        this.signOut = signOut;
        HourWorked = hourWorked;
        this.attendanceStatus = attendanceStatus;
    }
}
