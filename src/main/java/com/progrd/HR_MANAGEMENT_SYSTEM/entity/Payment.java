package com.progrd.HR_MANAGEMENT_SYSTEM.entity;


import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentMethod;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentStatus;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

// Payment:
//●PaymentID (Primary Key)
//●EmployeeID (Foreign Key)
//●Amount
//●PaymentDate
//●PaymentType (e.g., Daily, Monthly)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "payment")
@Table(schema = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @ManyToOne
    private Employee employee;
    private BigDecimal amount;
    private LocalDate paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String payPalTransactionId;



}
