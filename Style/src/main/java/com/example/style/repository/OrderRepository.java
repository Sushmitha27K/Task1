package com.example.style.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.style.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}