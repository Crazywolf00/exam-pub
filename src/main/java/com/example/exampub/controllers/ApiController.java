package com.example.exampub.controllers;

import com.example.exampub.models.Product;
import com.example.exampub.services.ProductService;
import com.example.exampub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> getUserByID(@PathVariable Optional<Long> id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id.get()));
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<?> getDrinkMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllDrinks());
    }


    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestParam Optional<Long> userID,
                                 @RequestParam Optional<Long> drinkID) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.orderMediation(productService.getProductByID(drinkID.get()), userID.get()));
    }
}
