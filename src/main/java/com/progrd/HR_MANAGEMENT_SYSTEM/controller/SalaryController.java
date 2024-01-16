package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.SalaryDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Salary;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/salary/{employeeId}")
    public ResponseEntity<List<Salary>> geteEmployeeSalaryById(@PathVariable Long id){
        return ResponseEntity.ok(salaryService.getEmployeeSalary(id));
    }

    @PostMapping("/salary")
    public ResponseEntity<Salary> addNewEmployeeSalary(SalaryDto salaryDto){
        return ResponseEntity.ok(salaryService.addSalary(salaryDto));
    }

    @PutMapping("/salary/{salaryId}")
    public ResponseEntity<Salary> UpdateSalaryDetails(@PathVariable Long id, Salary salary){
        return ResponseEntity.ok(salaryService.updateSalary(id, salary));
    }

    @DeleteMapping("salary/{salaryId}")
    public ResponseEntity<String> deleteSalary(@PathVariable Long id){
        return ResponseEntity.ok(salaryService.deleteSalary(id));
    }
}
