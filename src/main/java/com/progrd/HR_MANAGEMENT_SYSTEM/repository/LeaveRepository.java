package com.progrd.HR_MANAGEMENT_SYSTEM.repository;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {
}
