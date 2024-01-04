package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.repository.StaffRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffRatingService {
    @Autowired
    private StaffRatingRepository staffRatingRepository;

    @Autowired
    private  EmployeeService employeeService;


}
