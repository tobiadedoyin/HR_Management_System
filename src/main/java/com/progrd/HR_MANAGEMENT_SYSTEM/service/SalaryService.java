package com.progrd.HR_MANAGEMENT_SYSTEM.service;


import com.progrd.HR_MANAGEMENT_SYSTEM.dto.SalaryDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Salary;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.SalaryNotFound;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeRepository employeeRepository;

    public List<Salary> getEmployeeSalary(Long id){
        List<Salary> salary = salaryRepository.findByEmployeesId(id);
        return salary;
    }

    public Salary addSalary(SalaryDto salaryDto){
        Salary salary = new Salary();
        Employee employee = employeeService.getEmployeeById(salaryDto.getEmployeeId()).getBody();
        if(ObjectUtils.isEmpty(employee)){
            throw new SalaryNotFound("employee not found");
        }
        salary.setBasicSalary(salary.getBasicSalary());
        salary.setNetSalary(salaryDto.getNetSalary());
        salary.setAllowances(salaryDto.getAllowances());
        salary.setDeduction(salaryDto.getDeduction());

        salaryRepository.save(salary);
        return salary;
    }

    public Salary updateSalary(Long id, Salary salary){
        Salary update = salaryRepository.findById(id).orElseThrow(()->
                new SalaryNotFound("Salary is not available in our database"));

        update.setBasicSalary(salary.getBasicSalary());
        update.setNetSalary(salary.getNetSalary());
        update.setAllowances(salary.getAllowances());
        update.setDeduction(salary.getDeduction());

        return salaryRepository.save(update);
    }

    public String deleteSalary(Long id){
        Salary toDelete = salaryRepository.findById(id).orElseThrow(()->
                new SalaryNotFound("Salary is not available in our database"));
        salaryRepository.delete(toDelete);
        return "Salary has been Deleted";
    }


}
