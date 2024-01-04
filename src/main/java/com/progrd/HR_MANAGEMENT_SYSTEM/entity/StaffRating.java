package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class StaffRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employees;

    private String ratings;
    private LocalDate date;
}
