package com.example.exampub.models.dtos;

import com.example.exampub.models.UserOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserOrderDTO {

    private Long id;
    private String productName;
    private int amount;
    private double price;


    public UserOrderDTO(UserOrder userOrder) {
        this.id = userOrder.getId();
        this.productName = userOrder.getProductName();
        this.price = userOrder.getPrice();
        this.amount = userOrder.getAmount();
    }
}
