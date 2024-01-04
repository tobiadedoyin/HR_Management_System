package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/{leaveId}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable Integer leaveId){
        return leaveService.getLeaveById(leaveId);
    }

    @GetMapping("/employeeId/{id}")
    public List<Leave> getLeaveByEmployeeId(@PathVariable Integer id) {

        return leaveService.getLeaveByEmployeeId(id);
    }

    @PostMapping("/addLeave")
    public String addLeave(@RequestBody LeaveDto leaveDto){
        return leaveService.addLeave(leaveDto);
    }

    @PutMapping("/updateLeave/{leaveID}")
    public String updateLeave( @PathVariable Integer  id, @RequestBody LeaveDto leaveDto){
        return leaveService.updateLeave(id,leaveDto);
    }

    @DeleteMapping("/deleteLeave/{id}")
    public  String deleteLeave(@PathVariable Integer id){
        return leaveService.deleteLeave(id);
    }
}
