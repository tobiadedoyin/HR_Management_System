package com.progrd.HR_MANAGEMENT_SYSTEM.repository;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findByEmployeesId(long id);

}
