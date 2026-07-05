package com.home.prj.service.impl;

import com.home.prj.dto.request.RegisterRequest;
import com.home.prj.dto.response.UserResponse;
import com.home.prj.exception.UserAlreadyExistsException;
import com.home.prj.mapper.UserMapper;
import com.home.prj.repository.UserRepository;
import com.home.prj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        log.info("User registered successfully with email {}", request.email());
        validateDuplicateEmail(request.email()); // Check if the email already exists
        var user = UserMapper.toEntity(request); // Convert Record RegisterRequest to User entity
        user.setPassword(passwordEncoder.encode(request.password()));
        return UserMapper.toResponse(userRepository.save(user)); // save the user and convert to UserResponse
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            log.warn("Duplicate registration attempt for email {}", email);
            throw new UserAlreadyExistsException(
                    "User already exists with email : " + email);
        }
    }
}



//Why use existsByEmail() instead of findByEmail()?
//We only need to know whether a record exists. existsByEmail() avoids fetching the entire document, making it more efficient.