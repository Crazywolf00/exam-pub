package com.example.exampub.models.dtos;

import com.example.exampub.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDTO {

    private Long userId;

    private String userName;

    private boolean iaActive;

    private boolean isAdult;

    private double pocket;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.iaActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
    }


}
