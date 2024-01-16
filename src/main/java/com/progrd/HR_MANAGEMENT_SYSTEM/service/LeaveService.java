package com.progrd.HR_MANAGEMENT_SYSTEM.service;


import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.EmployeeDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.Status;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeService employeeService;

    private final  MailService mailservice;

    private final NotificationService notificationService;


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

        //alex added the employee entity
        leave.setEmployees(employee);

        leaveRepository.save(leave);

        //to send a mail notification to the hr for review
        assert employee != null;
        String fullName = employee.getLastName().concat(" "+employee.getFirstName());

        mailservice.sendPendingMessageForReview(fullName,leave.getLeaveId());
        //sending email notification to the employee
        mailservice.sendPendingMessage(fullName, employee.getEmail(), leave.getLeaveId());
        //save notification
        notificationService.savePendingLeaveNotification(employee.getId());

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

        //send email to employee
        assert employee != null;
        String fullName = employee.getLastName()+" "+employee.getFirstName();
        mailservice.sendApprovalMessage(fullName,employee.getEmail(),toUpdate.getLeaveId());
        //save notification
        notificationService.saveApprovedLeaveNotification(employee.getId());

        return("employee with leave id " + id+" has been successfully modified\n"+toUpdate);
    }



    public  String deleteLeave(Integer id){
        //to get employee details
        Leave toDecline = leaveRepository.findById(id).orElseThrow(null);
        Employee employee = toDecline.getEmployees();

        leaveRepository.deleteById(id);

        //send leave decline message to employee
        String fullName = employee.getLastName()+" "+employee.getFirstName();
        mailservice.sendDeleteMessage(fullName, employee.getEmail());
        //save notification
        notificationService.saveDeclineLeaveNotification(employee.getId());

        return ("employee with id " + id +" has been successfully deleted");
    }



}