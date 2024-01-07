package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import com.progrd.HR_MANAGEMENT_SYSTEM.paypalConfig.PaymentOrder;
import com.progrd.HR_MANAGEMENT_SYSTEM.paypalConfig.PaypalService;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.PaymentRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController  {


    @Autowired
    PaymentService paymentService;
    PaypalService paypalService;

    @PostMapping(value = "/init")
    public PaymentOrder createPayment(
            @RequestParam("sum") BigDecimal sum) {
        return paypalService.createPayment(sum);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping ("Payment/save")
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }
    @ResponseStatus(HttpStatus.OK )
    @GetMapping("Payment/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("paymentId/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
}
