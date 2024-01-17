package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.EmployeeDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Department;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Role;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    private final NotificationService notificationService;

    @Autowired
    private RoleRepository roleRepository;


    public ResponseEntity<Employee> getEmployeeById(long id) {
        return new ResponseEntity<>(employeeRepository.findById(id).get(), HttpStatus.OK);
    }

    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId()).getBody();
        Role role = roleRepository.findById(employeeDto.getRole()).get();

        employee.setJoiningDate(employeeDto.getJoiningDate());
        employee.setRole(role);
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setLastName(employeeDto.getLastName());
        employee.setGender(employeeDto.getGender());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDepartment(department);
        employee.setContactNumber(employeeDto.getContactNumber());
        employeeRepository.save(employee);

        notificationService.saveNotification(employee.getId());

        return ("employee successfully added\n" + employee);
    }

    public String updateEmployee(long id, EmployeeDto employeeDto) {
        Employee toUpdate = employeeRepository.findById(id).get();
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId()).getBody();
        Role role = roleRepository.findById(employeeDto.getRole()).get();
        toUpdate.setAddress(employeeDto.getAddress());
        toUpdate.setGender(employeeDto.getGender());
        toUpdate.setContactNumber(employeeDto.getContactNumber());
        toUpdate.setEmail(employeeDto.getEmail());
        toUpdate.setFirstName(employeeDto.getFirstName());
        toUpdate.setLastName(employeeDto.getLastName());
        toUpdate.setRole(role);
        toUpdate.setDateOfBirth(employeeDto.getDateOfBirth());
        toUpdate.setJoiningDate(employeeDto.getJoiningDate());
        toUpdate.setDepartment(department);

        employeeRepository.save(toUpdate);
        return ("employee with id " + id + " has been successfully modified\n" + toUpdate);
    }

    public String deleteEmployee(long id) {
        if (!employeeRepository.existsById(id)) {
            return ("employee with id " + id + " does not exist");
        } else {
            employeeRepository.deleteById(id);
        }
        return ("employee with id " + id + " has been successfully deleted");
    }

}
