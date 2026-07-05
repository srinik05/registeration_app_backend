package com.home.prj.service;

import com.home.prj.dto.request.LoginRequest;
import com.home.prj.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);
}
