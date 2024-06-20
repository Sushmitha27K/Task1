package com.example.email.controller;

import com.example.email.entity.Email;
import com.example.email.repository.EmailRepository;
import com.example.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/send-email")
    public String showEmailForm() {
        return "email_form";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam("subject") String subject, @RequestParam("message") String message, Model model) {
        List<Email> users = emailRepository.findAll();
        for (Email user : users) {
            emailService.sendSimpleMessage(user.getEmail(), subject, message);
        }
        return "success";
    }
}