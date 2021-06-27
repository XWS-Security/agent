package com.example.agent.controller;

import com.example.agent.controller.dto.OrderDto;
import com.example.agent.exceptions.NotEnoughItemsOnStockException;
import com.example.agent.model.Order;
import com.example.agent.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto order) {
        try {
            orderService.createOrder(modelMapper.map(order, Order.class));
            return new ResponseEntity<>("Order cerated successfully!", HttpStatus.OK);
        } catch (NotEnoughItemsOnStockException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }
}
