package com.example.paymentgatewayintegration.controller;

import com.example.paymentgatewayintegration.entity.Payment;
import com.example.paymentgatewayintegration.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public String showPaymentForm() {
        return "payment";
    }

    @PostMapping("/api/payments")
    public ModelAndView submitPayment(@RequestParam String orderId, @RequestParam Double amount) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);

        paymentService.processPayment(payment);

        return new ModelAndView("redirect:/success");
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}
