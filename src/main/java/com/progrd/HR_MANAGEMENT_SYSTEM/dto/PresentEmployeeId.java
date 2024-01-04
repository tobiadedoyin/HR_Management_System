package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class PresentEmployeeId {
    Long employeeId;

    public PresentEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
