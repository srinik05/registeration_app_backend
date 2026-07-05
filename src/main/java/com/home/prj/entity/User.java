package com.home.prj.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// @Data annotation: generates getters, setters, toString(), equals(), and hashCode() methods for the class. It is a convenient way to reduce boilerplate code in Java classes.
@Document(collection = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;

    @Builder.Default
    private Boolean active = true;   // for soft delete, default value is true

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();  // default value is current time

    private LocalDateTime updatedAt;
}
