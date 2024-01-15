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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employees;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    private Status status;
}
