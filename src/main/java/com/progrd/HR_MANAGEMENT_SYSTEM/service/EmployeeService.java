package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getEmployeeById(long id){
        return new ResponseEntity<>(employeeRepository.findById(id).get(), HttpStatus.OK);
    }

    public String addEmployee(Employee employee){
        employeeRepository.save(employee);
        return ("employee successfully added\n"+employee);
    }

    public String updateEmployee(long  id, Employee employee){
        Employee toUpdate = employeeRepository.findById(id).get();

        toUpdate.setAddress(employee.getAddress());
        toUpdate.setGender(employee.getGender());
        toUpdate.setContactNumber(employee.getContactNumber());
        toUpdate.setEmail(employee.getEmail());
        toUpdate.setFirstName(employee.getFirstName());
        toUpdate.setLastName(employee.getLastName());
        toUpdate.setRole(employee.getRole());
        toUpdate.setDateOfBirth(employee.getDateOfBirth());
        toUpdate.setJoiningDate(employee.getJoiningDate());

        employeeRepository.save(toUpdate);
        return("employee with id " + id+" has been successfully modified");
    }

    public String deleteEmployee(long id){
        if(!employeeRepository.existsById(id)){
            return ("employee with id " + id +" does not exist");
        }
        else {
            employeeRepository.deleteById(id);
        }
        return ("employee with id " + id +" has been successfully deleted");
    }

}
