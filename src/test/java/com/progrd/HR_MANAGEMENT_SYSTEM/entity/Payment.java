package com.progrd.HR_MANAGEMENT_SYSTEM.entity;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    private long paymentId;


    @ManyToOne


    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employeeId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Payment date is required")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;



}
