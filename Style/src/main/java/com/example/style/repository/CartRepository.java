package com.example.style.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.style.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
	void deleteByUserId(Long id);
}

