package com.home.prj.service.impl;

import com.home.prj.dto.request.RegisterRequest;
import com.home.prj.dto.response.UserResponse;
import com.home.prj.exception.UserAlreadyExistsException;
import com.home.prj.mapper.UserMapper;
import com.home.prj.repository.UserRepository;
import com.home.prj.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        validateDuplicateEmail(request.email()); // Check if the email already exists
        var user = UserMapper.toEntity(request); // Convert Record RegisterRequest to User entity
        return UserMapper.toResponse(userRepository.save(user)); // save the user and convert to UserResponse
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(
                    "User already exists with email : " + email);
        }
    }
}



//Why use existsByEmail() instead of findByEmail()?
//We only need to know whether a record exists. existsByEmail() avoids fetching the entire document, making it more efficient.