package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
    private double basicSalary;

    private double allowances;

    private double deduction;

    private double netSalary;

    private Long employeeId;
}
