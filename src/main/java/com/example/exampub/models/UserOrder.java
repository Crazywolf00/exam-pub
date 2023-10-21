package com.example.exampub.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserOrder {
    @Id
    @GeneratedValue
    @Column(name = "id_order")
    private Long id;
    private String productName;
    private double price;
    private int amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public UserOrder(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.amount = 1;
    }

    public void increaseAmount() {
        amount++;
    }
}
