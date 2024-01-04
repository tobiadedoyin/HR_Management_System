package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.EmployeeDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
@Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employees")
    public List<Employee> getAllEmployee(){
    return employeeService.getAllEmployee();
    }

    @GetMapping("/api/employees/{employeeID}")
    public ResponseEntity<Employee> getById(@RequestParam long id){
    return employeeService.getEmployeeById(id);
    }

    @PostMapping("/api/employees")
    public String createEmployee(@RequestBody EmployeeDto employeeDto){
    return employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/api/employees/{employeeID}")
    public String updateEmployee(@RequestParam long id, @RequestBody EmployeeDto employeeDto){
    return employeeService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/api/employees/{employeeID}")
    public String deleteEmployee(@RequestParam long id){
    return employeeService.deleteEmployee(id);
    }




}
