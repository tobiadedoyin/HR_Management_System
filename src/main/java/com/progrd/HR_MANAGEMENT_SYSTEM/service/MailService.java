package com.progrd.HR_MANAGEMENT_SYSTEM.service;

import com.progrd.HR_MANAGEMENT_SYSTEM.util.MailMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class MailService {

    public static final String PENDING_LEAVE_REQUEST = "Pending Leave Request";
    public static final String LEAVE_APPROVED = "Leave Approved";
    public static final String LEAVE_REQUEST_DECLINED = "Leave Request Declined";
    public static final String  HR_EMAIL_ADDRESS = "hr1@humanresurce.com";

    private final JavaMailSender javaMailSender;
    private final MailMessageUtil mailMessageUtil;
    @Value("${mail.verify.host}")
    private String host;
    @Value("${EMAIL-ADDRESS}")
    private String fromEmail;

    public void sendPendingMessageForReview(String name, int Lid){
       try {
           SimpleMailMessage mailMessage = new SimpleMailMessage();
           mailMessage.setTo(HR_EMAIL_ADDRESS);
           mailMessage.setSubject(PENDING_LEAVE_REQUEST);
           mailMessage.setFrom(fromEmail);
           mailMessage.setText(mailMessageUtil.getPendingMessageForReview(name, host, Lid));
           javaMailSender.send(mailMessage);
       }catch (Exception e){
           throw new RuntimeException("something went wrong");
       }

    }

    public void sendPendingMessage(String name, String to, int Lid){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setSubject(PENDING_LEAVE_REQUEST);
            mailMessage.setFrom(fromEmail);
            mailMessage.setText(mailMessageUtil.getPendingMessage(name, Lid));
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            throw new RuntimeException("something went wrong");
        }

    }

    public void sendApprovalMessage(String name, String to, int Lid){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(LEAVE_APPROVED);
        mailMessage.setFrom(fromEmail);
        mailMessage.setText(mailMessageUtil.getApprovalMessage(name, Lid));
        javaMailSender.send(mailMessage);

    }

    public void sendDeleteMessage(String name, String to){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(LEAVE_REQUEST_DECLINED);
        mailMessage.setFrom(fromEmail);
        mailMessage.setText(MailMessageUtil.getDeleteMessage(name));
        javaMailSender.send(mailMessage);

    }

}

