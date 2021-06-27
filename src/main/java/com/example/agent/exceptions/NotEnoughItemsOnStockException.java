package com.example.agent.exceptions;

public class NotEnoughItemsOnStockException extends RuntimeException {
    public NotEnoughItemsOnStockException(String name) {
        super("There is not enough " + name + " on stock for us to make this order.");
    }
}
