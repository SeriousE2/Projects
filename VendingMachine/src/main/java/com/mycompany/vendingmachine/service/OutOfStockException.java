package com.mycompany.vendingmachine.service;

public class OutOfStockException extends Exception {

    public OutOfStockException(String message) {
        super(message);
    }
}
