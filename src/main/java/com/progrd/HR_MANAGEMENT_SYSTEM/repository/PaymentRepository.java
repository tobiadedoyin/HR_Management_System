package com.progrd.HR_MANAGEMENT_SYSTEM.repository;;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {



}