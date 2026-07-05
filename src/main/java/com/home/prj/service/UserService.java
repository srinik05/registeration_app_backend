package com.home.prj.service;


import com.home.prj.dto.request.RegisterRequest;
import com.home.prj.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
}
