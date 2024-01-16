package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.DeductionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Deduction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long deductionId;
        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;

        private DeductionType deductionType;
        private double amount;


    }

