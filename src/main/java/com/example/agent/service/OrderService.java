package com.example.agent.service;

import com.example.agent.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);

    List<Order> getAgentOrders();
}
