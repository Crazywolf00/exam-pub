package com.example.exampub.models.dtos;

import com.example.exampub.models.User;
import com.example.exampub.models.UserOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class UserDTO {

    private Long userId;

    private String userName;

    private boolean iaActive;

    private boolean isAdult;

    private double pocket;

    private List<UserOrderDTO> orders;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.iaActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
        this.orders = creatOrdersDTO(user.getOrders());
    }

    public List<UserOrderDTO> creatOrdersDTO(List<UserOrder> userOrders) {
        List<UserOrderDTO> ordersDTO = new ArrayList<>();
        for (UserOrder order : userOrders) {
            ordersDTO.add(new UserOrderDTO(order));
        }
        return ordersDTO;
    }


}
