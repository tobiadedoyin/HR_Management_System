package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.Ratings;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.StaffRatingDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.StaffRating;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.AttendanceRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.StaffRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffRatingService {
    @Autowired
    private StaffRatingRepository staffRatingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;




    public String saveRating(StaffRatingDto staffRatingDto) {
        Employee employee = employeeRepository.findById(staffRatingDto.employeeId()).orElseThrow();
        List<Attendance> employeeAttendance = attendanceRepository
                .findAttendanceByEmployeesAndDateBetween(employee, staffRatingDto.sDate(), staffRatingDto.eDate());

        staffRatingRepository.save(new StaffRating(employee, rating(employeeAttendance), staffRatingDto.eDate()));

        return "Successfully added ratings";

    }

    private String rating(List<Attendance> attendances) {
        List<Ratings> employeeRatings = attendances.stream()
                .map(x -> new Ratings(x.getHourWorked()))
                .toList();

        double totalHourWorked = 0;

        for (Ratings rating : employeeRatings) {
            double rate = rating.getRating();

            if (rate > 9) {
                rate = 9;
            }
            totalHourWorked += rate;
        }
        double finalRating = (totalHourWorked / (attendances.size() * 9) ) * 5 ;
        return String.valueOf(String.format("%.2f", finalRating));
    }


    public ResponseEntity<List<StaffRating>> getRating() {
        try {
            return new ResponseEntity<>(staffRatingRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public ResponseEntity<StaffRating> getStaffRatingById(long id) {
       return new ResponseEntity<>(staffRatingRepository.findById(id).get(), HttpStatus.OK);
    }

    public String deleteRanting(long id) {
        staffRatingRepository.deleteById(id);
        return "Deleted successfully";
    }


}
