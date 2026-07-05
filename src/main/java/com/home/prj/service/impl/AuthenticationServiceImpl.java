package com.home.prj.service.impl;

import com.home.prj.dto.request.LoginRequest;
import com.home.prj.dto.response.LoginResponse;
import com.home.prj.entity.User;
import com.home.prj.exception.InvalidCredentialsException;
import com.home.prj.repository.UserRepository;
import com.home.prj.security.JwtService;
import com.home.prj.service.AuthenticationService;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid Email or Password"));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Invalid Email or Password");
        }

        log.info("User logged in successfully {}", user.getEmail());


//        return new LoginResponse(
//                "Dummy Token",
//                "Bearer",
//                "Login Successful");

        String token = jwtService.generateToken(user);
        return new LoginResponse(
                token,
                "Bearer",
                "Login Successful"
        );
    }
}
