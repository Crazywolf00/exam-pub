package com.example.exampub.controllers;

import com.example.exampub.services.ProductService;
import com.example.exampub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class ApiController {

    private final UserService userService;

    private final ProductService productService;


    @Autowired
    public ApiController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersNoOrderDTO());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        if (userService.getUserById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<?> getDrinkMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllDrinks());
    }


    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestParam Long userID,
                                 @RequestParam Long productID,
                                 @RequestParam int amount) {

        if (userService.getUserById(userID) == null || productService.getProductByID(productID) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(userService.getUserById(userID) == null ? "User not found" : "Product not found");
        }

        String result = userService.orderMediation(productService.getProductByID(productID), userID, amount);
        if (Objects.equals(result, "You are too yong") || Objects.equals(result, "You don't have enough money")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

    }
}
