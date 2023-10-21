package com.example.exampub.services;


import com.example.exampub.models.Product;
import com.example.exampub.models.Role;
import com.example.exampub.models.User;
import com.example.exampub.models.UserOrder;
import com.example.exampub.models.dtos.UserDTO;
import com.example.exampub.models.dtos.UserNoOrderDTO;
import com.example.exampub.repositories.OrderRepository;
import com.example.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userRepository.findByUserName(name).get().getUserId();
    }

    @Override
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<UserNoOrderDTO> getAllUsersNoOrderDTO() {
        List<UserNoOrderDTO> usersOnlyDTO = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getRole() == Role.USER) {
                usersOnlyDTO.add(new UserNoOrderDTO(user));
            }
        }
        return usersOnlyDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return new UserDTO(userRepository.getUserByUserId(id));
    }

    @Override
    public String orderMediation(Product product, Long userID) {
        User user = userRepository.getUserByUserId(userID);

        if (product.isForAdult()) {
            if (!(user.isAdult())) {
                return "You are too yong";
            }
        }

        if (product.getPrice() > user.getPocket()) {
            return "You don't have enough money";
        }


        for (UserOrder order : user.getOrders()) {
            if(Objects.equals(order.getProductName(), product.getProductName())) {
                order.increaseAmount();
                orderRepository.save(order);
                user.pay(order);
                userRepository.save(user);
                return "You bought a " + product.getProductName() + " for " + product.getPrice();
            }
        }

        UserOrder order = new UserOrder(product);
        order.setUser(user);
        orderRepository.save(order);
        user.addOrder(order);
        user.pay(order);
        userRepository.save(user);
        return "You bought a " + product.getProductName() + " for " + product.getPrice();
    }


}

