package com.example.paymentgatewayintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paymentgatewayintegration.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
