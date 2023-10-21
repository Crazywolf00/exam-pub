package com.example.exampub.services;

import com.example.exampub.models.dtos.OrdersInfoDTO;
import com.example.exampub.models.dtos.ProductInfoDTO;
import com.example.exampub.models.dtos.UserDTO;

import java.util.List;

public interface OrderService {

    List<ProductInfoDTO> getProductsInfo();

    List<OrdersInfoDTO> getOrdersInfo();

    List<UserDTO> getAllUsersOrderInfo();

}
