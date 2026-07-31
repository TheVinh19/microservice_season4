package com.example.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderServiceController {
    private final OrderService orderService;
    public OrderServiceController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable String productId){
        return orderService.getProductFromProductService(productId);
    }
}
