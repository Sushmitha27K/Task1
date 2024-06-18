package com.example.style.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.style.entity.Cart;
import com.example.style.entity.Order;
import com.example.style.entity.Product;
import com.example.style.entity.User;
import com.example.style.repository.CartRepository;
import com.example.style.repository.OrderRepository;
import com.example.style.repository.ProductRepository;
import com.example.style.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    
    @Transactional
    public void addToCart(Long userId, Long productId) {
        Cart cart = new Cart();

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        cart.setUser(user);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        cart.setProduct(product);

        cartRepository.save(cart);
    }

    @Transactional
    public void removeFromCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }
    
    @Transactional
    public int getCartCount(Long userId) {
        return cartRepository.findByUserId(userId).size();
    }
    
    
    public void placeOrder(User user, double totalAmount) {
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}

