package com.example.exampub.controllers;

import com.example.exampub.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
public class OwnerController {

    private final OrderService orderService;

    @Autowired
    public OwnerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProductsInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getProductsInfo());
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllOrdersInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersInfo());
    }
    @GetMapping("/user")
    public ResponseEntity<?> getAllUsersOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllUsersOrderInfo());
    }
}
