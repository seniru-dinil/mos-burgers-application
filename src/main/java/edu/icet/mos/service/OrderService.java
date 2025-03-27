package edu.icet.mos.service;

import edu.icet.mos.dto.Order;

import java.util.List;

public interface OrderService {
    void placeOrder(Order order);
    List<Order> getAll();
}
