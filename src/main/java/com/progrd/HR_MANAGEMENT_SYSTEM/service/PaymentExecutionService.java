package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.PaymentExecution;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.PaymentRepository;

public class PaymentExecutionService {

    private final APIContext apiContext;

    PaymentRepository paymentRepository;

    public PaymentExecutionService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public PaymentExecution executePayPalPayment(Long paymentId, String approvalUrl) throws PayPalRESTException {
        Payment payment = paymentRepository.getPaymentByPaymentId(paymentId);
                PaymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(approvalUrl.substring(approvalUrl.lastIndexOf("/") + 1));
        return payment.create(apiContext, paymentExecution);
    }

    public String getState(PaymentExecution paymentExecution) {
        return paymentExecution.getState();
    }

    public String getId(PaymentExecution paymentExecution) {
        return paymentExecution.getId();
    }
}
