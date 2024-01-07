package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.PaymentDTO;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.PaymentExecution;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentMethod;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentStatus;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.PaymentType;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.PayPalRESTException;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeService employeeService;
    private final APIContext payPalContext; // PayPal API context

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, EmployeeService employeeService, APIContext payPalContext) {
        this.paymentRepository = paymentRepository;
        this.employeeService = employeeService;
        this.payPalContext = payPalContext;
    }

    @Transactional
    public PaymentDTO initiatePayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        Employee employee = employeeService.getEmployeeById(paymentDTO.getEmployeeId()).getBody();
        payment.setEmployee(employee);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentType(PaymentType.valueOf(paymentDTO.getPaymentType()));
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentMethod(PaymentMethod.valueOf(String.valueOf(paymentDTO.getPaymentMethod())));

        if (payment.getPaymentMethod() == PaymentMethod.PAYPAL) {
            try {
                Payment createdPayment = paymentRepository.save(payment);
                PaymentExecution paymentExecution = executePayPalPayment(createdPayment.getPaymentId(), paymentDTO.getPaypalApprovalUrl());
                if (paymentExecution.getState().equals("approved")) {
                    payment.setPaymentStatus(PaymentStatus.COMPLETED);
                    payment.setPayPalTransactionId(paymentExecution.getId());
                } else {
                    payment.setPaymentStatus(PaymentStatus.FAILED);
                }
            } catch (PayPalRESTException e) {
                e.printStackTrace();
            }
        } else {
            // Process other payment methods (e.g., bank transfer)
        }

        Payment savedPayment = paymentRepository.save(payment);
        return mapToDTO(savedPayment);
    }
    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setEmployeeId(payment.getEmployee().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentType(payment.getPaymentType().name());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus().name());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod().name());

        return paymentDTO;
    }


    private PaymentExecution executePayPalPayment(Long paymentId, String approvalUrl) throws PayPalRESTException {
        Payment payment = paymentRepository.getById(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setId(approvalUrl.substring(approvalUrl.lastIndexOf("/") + 1));
        return payment.create(payPalContext, paymentExecution);
    }

    public ResponseEntity<Payment> getPaymentById(long id){
        return new ResponseEntity<>(paymentRepository.findById(id).get(), HttpStatus.OK) ;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentRepository.findAll());
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
