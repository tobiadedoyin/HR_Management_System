package com.progrd.HR_MANAGEMENT_SYSTEM.repository;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {
   List<Leave> findByEmployeesId(Integer employeeId);
}
