package com.example.exampub.services;

import com.example.exampub.models.Product;
import com.example.exampub.models.User;
import com.example.exampub.models.UserOrder;
import com.example.exampub.models.dtos.OrdersInfoDTO;
import com.example.exampub.models.dtos.ProductInfoDTO;
import com.example.exampub.models.dtos.UserDTO;
import com.example.exampub.repositories.OrderRepository;
import com.example.exampub.repositories.ProductRepository;
import com.example.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ProductInfoDTO> getProductsInfo() {

        List<ProductInfoDTO> productsDTO = new ArrayList<>();

        for (Product product: productRepository.findAll()) {
            productsDTO.add(new ProductInfoDTO(product));
        }

        for (UserOrder order: orderRepository.findAll()) {
            for (ProductInfoDTO productDTO: productsDTO) {
                if(Objects.equals(order.getProductName(), productDTO.getProductName())) {
                    productDTO.increaseAmount(order.getAmount());
                    break;
                }
            }
        }

        for (ProductInfoDTO productDTO: productsDTO) {
            productDTO.countSummaryPrice();
        }

        return productsDTO;
    }

    @Override
    public List<OrdersInfoDTO> getOrdersInfo() {
        List<OrdersInfoDTO> ordersInfoDTO = new ArrayList<>();

        for (Product product: productRepository.findAll()) {
            ordersInfoDTO.add(new OrdersInfoDTO(product));
        }

        for (UserOrder order: orderRepository.findAll()) {
            for (OrdersInfoDTO orderInfoDTO: ordersInfoDTO) {
                if(Objects.equals(order.getProductName(), orderInfoDTO.getProductName())) {
                    orderInfoDTO.addOrder(order);
                    break;
                }
            }
        }

        return ordersInfoDTO;
    }

    @Override
    public List<UserDTO> getAllUsersOrderInfo() {
        List<UserDTO> usersOrdersDTO = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            usersOrdersDTO.add( new UserDTO(user));
        }
        return usersOrdersDTO;
    }



}
