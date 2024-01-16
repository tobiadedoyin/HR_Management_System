package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.LeaveType;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.Status;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LeaveDto {
    private Date startDate;

    private Date endDate;

    private LeaveType leaveType;

    private Long employeeId;

    public void setStatus(Status status) {
    }
}
