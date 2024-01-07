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

    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
    return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{employeeID}")
    public ResponseEntity<Employee> getById(@PathVariable long employeeID){
    return employeeService.getEmployeeById(employeeID);
    }

    @PostMapping("/employee")
    public String createEmployee(@RequestBody EmployeeDto employeeDto){
    return employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/employee/{employeeID}")
    public String updateEmployee(@PathVariable long employeeID, @RequestBody EmployeeDto employeeDto){
    return employeeService.updateEmployee(employeeID, employeeDto);
    }

    @DeleteMapping("/employee/{employeeID}")
    public String deleteEmployee(@RequestParam long id){
    return employeeService.deleteEmployee(id);
    }




}
