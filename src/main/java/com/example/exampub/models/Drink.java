package com.example.exampub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Drink {
    @Id
    @GeneratedValue
    @Column(name = "id_drink")
    private Long id;

    private String productName;
    private double price;
    private boolean isForAdult;

    public Drink(String productName, double price, boolean isForAdult) {
        this.productName = productName;
        this.price = price;
        this.isForAdult = isForAdult;
    }
}
