package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.Check;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.AttendanceStatus;
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

    public List<AttendanceDto> getAttendanceByEmployeeId(long employeeId) {
        try {
            return attendanceRepository.findByEmployeesId(employeeId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AttendanceDto> getAttendanceByDate(LocalDate date) {
        try {
            return attendanceRepository.findAttendanceDtoByDate(date);
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
            LocalTime resumptionTime = LocalTime.of(8, 0);
            LocalTime signInTime = attendance
                    .getSignIn()
                    .isBefore(resumptionTime) ? resumptionTime : attendance.getSignIn();
            Duration duration = Duration.between(signInTime, signOutTime);

            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            double hourWorked = hours + ((double) minutes / 60);
            double totalHourWorked = hourWorked > 9.5 || hourWorked <= 9 ? hourWorked : 9;

            attendance.setSignOut(signOutTime);
            attendance.setHourWorked(Double.parseDouble(String.format("%.2f", totalHourWorked)));
            attendanceRepository.save(attendance);

            return ResponseEntity.ok(Map.of("message", "signed out"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(Map.of("message", "something went wrong"), HttpStatus.OK);
    }

    private Attendance employeeSignIn(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        return new Attendance(
                employee, LocalDate.now(),
                LocalTime.now(ZoneId.of("Africa/Lagos")),
                null, 0, AttendanceStatus.PRESENT);
    }

    public ResponseEntity<List<Attendance>> getAttendanceForDateRange(long id, LocalDate sDate, LocalDate eDate) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(attendanceRepository.findAttendanceByEmployeesAndDateBetween(employee, sDate, eDate), HttpStatus.OK);
    }

    public ResponseEntity<List<Attendance>> getAttendanceOfAllEmployeeForDateRange(LocalDate sDate, LocalDate eDate) {
        return new ResponseEntity<>(attendanceRepository.findAttendanceByDateBetween(sDate, eDate), HttpStatus.OK);
    }
}
