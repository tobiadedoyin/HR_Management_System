package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.PaymentNotFoundException;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;



@Service
public class PaymentService {

     private PaymentRepository paymentRepository;
     @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
         try {
             return paymentRepository.findAll();
         }catch (Exception e){
             e.printStackTrace();
         }
          return new ArrayList<>();
    }

    public Payment getPaymentById(Long paymentId) {
         try {
             return paymentRepository.findById(paymentId).orElseThrow();
         }catch (Exception e){
             e.printStackTrace();
         }
         return null;
    }





}
