package com.example.email.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to,String subject, String text) {
       
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("sushmitha2721.k@gmail.com");
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            mailSender.send(mailMessage);
        
    }
    
}