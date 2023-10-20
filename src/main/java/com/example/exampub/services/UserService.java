package com.example.exampub.services;


import com.example.exampub.models.dtos.UserDTO;

import java.util.List;

public interface UserService {
    Long getCurrentUserId();

    String getCurrentUserName();

    List<UserDTO> getAllUsersDTO();


}
