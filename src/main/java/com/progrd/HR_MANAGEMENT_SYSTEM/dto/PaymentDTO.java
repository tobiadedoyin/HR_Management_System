package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentMethod;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentStatus;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class PaymentDTO {
    private Long paymentId;
    private Long employeeId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentType;
    private String paymentStatus;
    private String paymentMethod;
    private String payPalTransactionId;
    private String paypalApprovalUrl;



}