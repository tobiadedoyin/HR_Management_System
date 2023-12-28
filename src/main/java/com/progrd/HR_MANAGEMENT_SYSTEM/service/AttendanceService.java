package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.Check;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Status;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.AttendanceRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    public List<Attendance> getAllAttendance() {
        try {
            return attendanceRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Attendance getAttendanceByEmployeeId(long employeeId) {
        try {
            return attendanceRepository.findById(employeeId).orElseThrow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Attendance> getAttendanceByDate(String date) {
        try {
            return attendanceRepository.findAttendanceByDate(LocalDate.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ResponseEntity<Map<String, String>> signIn(Check employeeId) {
        try {
            attendanceRepository.save(employeeSignIn(employeeId.employeeId()));

            return ResponseEntity.ok(Map.of("message", "signed in"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(Map.of("message","Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Map<String, String>> signOut(long id) {
        try {
            Attendance attendance = attendanceRepository.findById(id).orElseThrow();

            LocalTime signOutTime = LocalTime.now(ZoneId.of("Africa/Lagos"));
            LocalTime signInTime = attendance.getSignIn();
            Duration duration = Duration.between(signInTime, signOutTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            double hourWorked = hours + ((double) minutes / 60);
            double totalHourWorked = hourWorked > 9.5 ? hourWorked : 9;

            attendance.setSignOut(signOutTime);
            attendance.setHourWorked(totalHourWorked);
            attendanceRepository.save(attendance);

            return ResponseEntity.ok(Map.of("message", "signed out"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(Map.of("message", "something went wrong"), HttpStatus.OK);
    }

    private Attendance employeeSignIn(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        LocalDate currentDate = LocalDate.now();
        LocalTime signInTime = LocalTime.now(ZoneId.of("Africa/Lagos"));
        long hour = 0;
        int timeDifference = LocalTime.of(8, 30).compareTo(signInTime);
        Status status;

        if (timeDifference >= 0) {
            status = Status.PRESENT;
        } else {
            status = Status.LATE;
        }
        log.info("Inside status {}", status);
        return new Attendance(employee, currentDate, signInTime, null, hour, status);
    }


}
