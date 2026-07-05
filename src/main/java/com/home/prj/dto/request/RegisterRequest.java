package com.home.prj.dto.request;


// This is a DTO (Data Transfer Object) class for user registration requests. It contains the necessary fields for registering a new user, such as first name, last name, email, mobile number, and password. The class is defined as a record, which is a special kind of class in Java that is immutable and provides a concise syntax for defining data carriers.
// using record feature : No getters. No setters, No constructors, No equals(), No hashCode(), No toString() Java generates them automatically.

import jakarta.validation.constraints.*;
public record RegisterRequest(
        @NotBlank(message = "First Name is required")
        String firstName,

        @NotBlank(message = "Last Name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email")
        String email,

        @NotBlank(message = "Mobile Number is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Mobile Number must contain exactly 10 digits"
        )
        String mobile,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password

) {
}
