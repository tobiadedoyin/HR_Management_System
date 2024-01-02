package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.DepartmentDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Department;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.DepartmentNotFoundException;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public ResponseEntity<List<Department>> getAllDepartment() {
        try {
            return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Department> getDepartmentById(long id) {
        try {
            return new ResponseEntity<>(departmentRepository.findById(id)
                    .orElseThrow(DepartmentNotFoundException::new), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Map<String, String> addNewDepartment(DepartmentDto department) {
        try {
            log.info("Name: {}", department.departmentName());
            departmentRepository.save(new Department(department.departmentName()));

            return Map.of("message", "Successfully added a new Department");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Map.of("message", "Something went wrong");
    }

    public Map<String, String> updateDepartment(long id, DepartmentDto department) {
        try {
            Department departmentToBeUpdated = departmentRepository.findById(id)
                    .orElseThrow(DepartmentNotFoundException::new);

            departmentToBeUpdated.setName(department.departmentName());

            departmentRepository.save(departmentToBeUpdated);

            return Map.of("message", "Successfully update Department");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Map.of("message", "Something went wrong");
    }

    public Map<String, String> deleteDepartment(long id) {
        try {
            departmentRepository.deleteById(id);

            return Map.of("message", "Successfully delete Department");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Map.of("message", "Something went wrong");
    }
}
