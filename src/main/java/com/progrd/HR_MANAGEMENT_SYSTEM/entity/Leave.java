package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.LeaveType;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue
    private Integer leaveId;

    private Date startDate;

    private Date endDate;

    @Enumerated
    private LeaveType leaveType;

    @Enumerated
    private Status status;

}
