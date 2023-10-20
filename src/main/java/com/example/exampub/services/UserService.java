package com.example.exampub.services;


import com.example.exampub.models.dtos.UserDTO;
import com.example.exampub.models.dtos.UserNoOrderDTO;

import java.util.List;

public interface UserService {
    Long getCurrentUserId();

    String getCurrentUserName();

    List<UserNoOrderDTO> getAllUsersNoOrderDTO();
    UserDTO getUserById(Long id);


}
