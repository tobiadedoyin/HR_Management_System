package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Deduction;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.AttendanceStatus;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.DeductionType;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.DeductionNotFoundException;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.Exception;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.AttendanceRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.DeductionRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DeductionService {
    AttendanceService attendanceService;
    AttendanceRepository attendanceRepository;
    EmployeeRepository employeeRepository;


    DeductionRepository deductionRepository;


    public List<Deduction> getAllDeductions() {
        return deductionRepository.findAll();
    }

    public Deduction getDeductionById(Long deductionId) {
        return deductionRepository.findById(deductionId).orElseThrow(() -> new RuntimeException("NOt "));
    }
    public Deduction createDeduction( Deduction deduction) {

        return  deductionRepository.save(deduction);
    }
    public void deleteDeduction(Long deductionId) {
        deductionRepository.deleteById(deductionId);
    }
    public ResponseEntity<Map<String, String>> calculateDeduction(Long employeeId, LocalDate startDate, LocalDate endDate) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByEmployeesAndDateBetween(employee, startDate, endDate);


        double totalDeduction = calculateDeductionFromAttendance(attendanceList);

        Deduction deduction = new Deduction();
        deduction.setEmployee(employee);
        deduction.setDeductionType(DeductionType.HOURLY);
        deduction.setAmount(totalDeduction);

        deductionRepository.save(deduction);

        return ResponseEntity.ok(Map.of("message", "Deduction calculated and saved"));

    }


    private double calculateDeductionFromAttendance(List<Attendance> attendanceList) {
        double totalDeduction = 0.0;
        double hourlyRate = 10.0;

        for (Attendance attendance : attendanceList) {
            double hoursWorked = attendance.getHourWorked();
            AttendanceStatus status = AttendanceStatus.valueOf(attendance.getAttendanceStatus());

            if (status == AttendanceStatus.ABSENT) {

                totalDeduction += 50.0;
            } else {

                if (hoursWorked < 9) {

                    totalDeduction += (8 - hoursWorked) * hourlyRate;
                }

            }
        }

        return totalDeduction;
    }
}



    

