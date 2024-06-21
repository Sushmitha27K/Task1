package com.example.paymentgatewayintegration.service;

import com.example.paymentgatewayintegration.entity.Payment;
import com.example.paymentgatewayintegration.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Value("${payment.gateway.api-key}")
    private String apiKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        // Set the API key for Stripe or other payment gateways
        Stripe.apiKey = apiKey;
    }

    public Payment processPayment(Payment payment) {
        try {
            // Example using Stripe
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount((long) (payment.getAmount() * 100)) // Amount in cents
                    .setCurrency("usd")
                    .setDescription("Charge for order " + payment.getOrderId())
                    .setSource("tok_visa") // Token representing payment method
                    .build();

            Charge charge = Charge.create(params);
            payment.setStatus(charge.getStatus());

        } catch (StripeException e) {
            e.printStackTrace();
            payment.setStatus("failed");
        }

        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment updatePaymentStatus(Long id, Payment paymentDetails) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus(paymentDetails.getStatus());
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id " + id);
        }
    }

    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}