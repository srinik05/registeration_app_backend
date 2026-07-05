package com.home.prj.mapper;

import com.home.prj.dto.request.RegisterRequest;
import com.home.prj.dto.response.UserResponse;
import com.home.prj.entity.User;

//Instead of manually copying fields in multiple places, we'll centralize the conversion logic.
public class UserMapper {

    private UserMapper(){

    }

    public static User toEntity(RegisterRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .mobile(request.mobile())
                .password(request.password())  //Bcrypt will be added later in the service layer before saving to the database.
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobile(),
                user.getActive()
        );
    }
}
