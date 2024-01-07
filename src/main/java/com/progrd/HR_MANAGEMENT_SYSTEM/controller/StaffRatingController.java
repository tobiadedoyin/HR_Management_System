package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.StaffRatingDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.StaffRating;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.StaffRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffRating")
public class StaffRatingController {

    @Autowired
    private  StaffRatingService staffRatingService;

    @PostMapping("/saveRating")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveRating(@RequestBody StaffRatingDto staffRatingDto) {
        return staffRatingService.saveRating(staffRatingDto);
    }

    @GetMapping("/getRatings")
    public ResponseEntity<List<StaffRating>> getAllEmployee(){
        return staffRatingService.getRating();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffRating> getStaffRatingById(@PathVariable long id) {
        return staffRatingService.getStaffRatingById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteRating(@PathVariable long id) {
      return staffRatingService.deleteRanting(id);

    }


}
