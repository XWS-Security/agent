package com.example.agent.service.impl;

import com.example.agent.model.Agent;
import com.example.agent.model.Order;
import com.example.agent.model.OrderItem;
import com.example.agent.model.Product;
import com.example.agent.repository.OrderItemRepository;
import com.example.agent.repository.OrderRepository;
import com.example.agent.repository.ProductRepository;
import com.example.agent.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createOrder(Order order) {
        List<OrderItem> items = new ArrayList<>();
        List<Product> modifiedProducts = new ArrayList<>();
        order.getItems().forEach(orderItem -> {
            Optional<Product> optional = productRepository.findById(orderItem.getId());
            if (optional.isPresent()) {
                Product product = optional.get();
                OrderItem item = new OrderItem(product, orderItem.getAmount(), product.getPrice());
                items.add(item);
                product.decreaseQuantity(item.getAmount());
                modifiedProducts.add(product);
            }
        });

        productRepository.saveAll(modifiedProducts);
        orderItemRepository.saveAll(items);
        order.setItems(items);
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAgentOrders() {
        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findByAgentId(agent.getId());
    }
}
