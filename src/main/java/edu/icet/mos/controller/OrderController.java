package edu.icet.mos.controller;


import edu.icet.mos.dto.Order;
import edu.icet.mos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public void placeOrder(@RequestBody Order order){
        service.placeOrder(order);
    }

    @GetMapping("/list")
    public List<Order> getAll(){
        return service.getAll();
    }
}
