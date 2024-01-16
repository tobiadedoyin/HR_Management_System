package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import lombok.Data;

import java.time.LocalDate;


public record StaffRatingDto(long employeeId, LocalDate sDate, LocalDate eDate) { }

