package com.progrd.HR_MANAGEMENT_SYSTEM.controller;


import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.PaymentRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController  {


    @Autowired
    PaymentService paymentService;

   
     @ResponseStatus(HttpStatus.CREATED)
    @PutMapping ("Payment/save")
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }
     @ResponseStatus(HttpStatus.OK )
    @GetMapping("allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("paymentId/{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
}
