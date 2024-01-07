package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.EmployeeDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Department;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.Status;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeService employeeService;



    public ResponseEntity<Leave> getLeaveById(Integer id) {
        return new ResponseEntity<>(leaveRepository.findById(id).get(), HttpStatus.OK);
    }

    public List<Leave> getLeaveByEmployeeId(Integer employeeId) {

            return leaveRepository.findByEmployeesId(employeeId);
    }

    public String addLeave(LeaveDto leaveDto) {
        Leave leave = new Leave();
        Employee employee = employeeService.getEmployeeById(leaveDto.getEmployeeId()).getBody();
        leave.setStartDate(leaveDto.getStartDate());
        leave.setEndDate(leaveDto.getEndDate());
        leave.setLeaveType(leaveDto.getLeaveType());
        leave.setStatus(Status.PENDING);
        leave.setEmployees(employee);

         leaveRepository.save(leave);
        return "leave successfully requested";
    }

    public String updateLeave(Integer  id, LeaveDto leaveDto) {
        Leave toUpdate = leaveRepository.findById(id).get();
        Employee employee = employeeService.getEmployeeById(leaveDto.getEmployeeId()).getBody();

        toUpdate.setStartDate(leaveDto.getStartDate());
        toUpdate.setEndDate(leaveDto.getEndDate());
        toUpdate.setLeaveType(leaveDto.getLeaveType());
        toUpdate.setStatus(Status.APPROVED);

        leaveRepository.save(toUpdate);
        return("employee with leave id " + id+" has been successfully modified\n"+toUpdate);
    }


    public  String deleteLeave(Integer id) {
        leaveRepository.deleteById(id);
        return ("employee with id " + id +" has been successfully deleted");
    }



}