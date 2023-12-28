package com.progrd.HR_MANAGEMENT_SYSTEM;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Department;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.DepartmentRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HrManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(HrManagementSystemApplication.class, args);

		System.out.println("running");
	}
}
