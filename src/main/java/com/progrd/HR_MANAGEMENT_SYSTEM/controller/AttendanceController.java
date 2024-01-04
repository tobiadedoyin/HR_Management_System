package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.Check;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
    @GetMapping("employeeId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AttendanceDto> getAttendanceByEmployeeId(@PathVariable long id) {
        return attendanceService.getAttendanceByEmployeeId(id);
    }
    @GetMapping("/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<AttendanceDto> getAttendanceByDate(@PathVariable LocalDate date) {
        return attendanceService.getAttendanceByDate(date);
    }

    @GetMapping("/timeFrame/{id}/{sDate}/{eDate}")
    public ResponseEntity<List<Attendance>> getAttendanceOfEmployeeForDateRange
            (@PathVariable long id,
             @PathVariable LocalDate sDate, @PathVariable LocalDate eDate) {
                return attendanceService.getAttendanceForDateRange(id, sDate, eDate);
    }

@GetMapping("/range/{sDate}/{eDate}")
    public ResponseEntity<List<Attendance>> getAttendanceOfForDateRange
            (@PathVariable LocalDate sDate, @PathVariable LocalDate eDate) {
                return attendanceService.getAttendanceOfAllEmployeeForDateRange(sDate, eDate);
    }


    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Check employeeId) {
        return attendanceService.signIn(employeeId);
    }
    @PutMapping("/signOut/{id}")
    public ResponseEntity<Map<String, String>> signOut(@PathVariable long id) {
        return attendanceService.signOut(id);
    }


}
