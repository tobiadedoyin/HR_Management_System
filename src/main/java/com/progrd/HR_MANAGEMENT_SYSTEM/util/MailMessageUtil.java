package com.progrd.HR_MANAGEMENT_SYSTEM.util;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LeaveDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Leave;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailMessageUtil {
    private  final LeaveRepository leaveRepository;
    //private final LeaveDto leaveDto;

    public String getPendingMessageForReview(String name, String host, int Lid){
        Leave leave1 =leaveRepository.findById(Lid).get();
        LeaveDto leave_detail = new LeaveDto();
        leave_detail.setLeaveType(leave1.getLeaveType());
        leave_detail.setStartDate(leave1.getStartDate());
        leave_detail.setEmployeeId(leave1.getEmployees().getId());
        leave_detail.setEndDate(leave1.getEndDate());

        return ("Dear "+name+"\n\nyour leave applications as seen below is awaiting review\n\n"+leave_detail
                + "\n\nuse this link to approve "+getApprovalLink(host, Lid)+"\nOR\nthis link to reject and delete "
                +getRejectionLink(host, Lid)+"\n\n\nThank you.");
    }

    public String getPendingMessage(String name, int Lid){
        Leave leave =leaveRepository.findById(Lid).get();
        LeaveDto leaveDto = new LeaveDto();
        leaveDto.setLeaveType(leave.getLeaveType());
        leaveDto.setStartDate(leave.getStartDate());
        leaveDto.setEmployeeId(leave.getEmployees().getId());
        leaveDto.setEndDate(leave.getEndDate());

        return ("Dear "+name+"\n\nyour leave applications as seen below is awaiting review\n\n"+leaveDto
                +"\n\n\nThank you.");
    }


    private static String getApprovalLink(String host, int Lid) {
        return (host+"/api/leave/"+Lid);
    }

    public static String getRejectionLink(String host, int Lid) {
        return (host+"/api/deleteleave/"+Lid);
    }

    public  String getApprovalMessage(String name, int Lid){
        Leave leave =leaveRepository.findById(Lid).get();
        return ("Dear "+name+"\n\nyour leave applications as seen below \n\n"+leave
                + "\n\nhas been approved\n\nThank you");
    }

    public static String getDeleteMessage(String name) {
        return ("Dear " + name + "\n\nyour leave applications has been DECLINED \n"
                + "please reach out to the HR-team before applying again\n\nThank you.");
    }
}
