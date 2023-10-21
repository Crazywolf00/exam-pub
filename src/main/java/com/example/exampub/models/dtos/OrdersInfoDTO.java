package com.example.exampub.models.dtos;

import com.example.exampub.models.Product;
import com.example.exampub.models.UserOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class OrdersInfoDTO {

    private String productName;

    private List<OneOrderInfoDTO> orders = new ArrayList<>();

    public OrdersInfoDTO(Product product) {
        this.productName = product.getProductName();
    }

    public void addOrder(UserOrder order) {
        orders.add(new OneOrderInfoDTO(order));
    }
}
