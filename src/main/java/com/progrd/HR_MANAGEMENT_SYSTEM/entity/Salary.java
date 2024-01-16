package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;

    @Column(name = "basic_salary")
    private double basicSalary;

    private double allowances;

    private double deduction;

    @Column(name = "net_salary")
    private double netSalary;

//    SalaryID (Primary Key)
//    EmployeeID (Foreign Key)
//    BasicSalary
//    Allowances
//    Deductions
//    NetSalary

}
