package com.home.prj.controller;

import com.home.prj.dto.request.RegisterRequest;
import com.home.prj.dto.response.UserResponse;
import com.home.prj.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")   //This allows your React application running on port 3000 to call the backend on 8080.
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        // Implement the logic to handle user registration
        UserResponse response = userService.registerUser(request); // Replace null with actual RegisterRequest
        // Return appropriate response to the client
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
