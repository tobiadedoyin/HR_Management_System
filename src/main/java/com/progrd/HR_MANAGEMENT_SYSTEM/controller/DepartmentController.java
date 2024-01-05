package com.progrd.HR_MANAGEMENT_SYSTEM.controller;


import com.progrd.HR_MANAGEMENT_SYSTEM.dto.DepartmentDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Department;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> addNewDepartment(@RequestBody DepartmentDto department) {
        return departmentService.addNewDepartment(department);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> updateDepartment(@PathVariable long id,
                                                    @RequestBody DepartmentDto department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deleteDepartment(@PathVariable long id) {
        return departmentService.deleteDepartment(id);
    }
}
