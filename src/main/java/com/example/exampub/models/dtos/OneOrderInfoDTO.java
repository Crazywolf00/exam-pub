package com.example.exampub.models.dtos;

import com.example.exampub.models.UserOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OneOrderInfoDTO {

    private Long userID;
    private int amount;
    private double price;


    public OneOrderInfoDTO(UserOrder userOrder) {
        this.userID = userOrder.getUser().getUserId();
        this.amount = userOrder.getAmount();
        this.price = userOrder.getPrice();
    }
}
