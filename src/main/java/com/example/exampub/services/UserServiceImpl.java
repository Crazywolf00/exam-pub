package com.example.exampub.services;


import com.example.exampub.models.Role;
import com.example.exampub.models.User;
import com.example.exampub.models.dtos.UserDTO;
import com.example.exampub.models.dtos.UserNoOrderDTO;
import com.example.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        for (User user:userRepository.findAll()) {
            if(user.getRole() == Role.USER) {
                usersOnlyDTO.add(new UserNoOrderDTO(user));
            }
        }
        return usersOnlyDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return new UserDTO(userRepository.getUserByUserId(id));
    }


}

