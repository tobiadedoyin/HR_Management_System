package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/api/leave")
    public ResponseEntity<Leave> getLeaveById(Integer id){
        return leaveService.getLeaveById(id);
    }

    @PostMapping("/api/leave")
    public String addLeave(@RequestBody LeaveDto leaveDto){
        return leaveService.addLeave(leaveDto);
    }

    @PutMapping(" /api/leave/{leaveID}")
    public String updateLeave( @PathVariable Integer  id, @RequestBody LeaveDto leaveDto){
        return leaveService.updateLeave(id,leaveDto);
    }

    @DeleteMapping(" /api/leave/{leaveID}")
    public  String deleteLeave(@PathVariable Integer id){
        return leaveService.deleteLeave(id);
    }
}
