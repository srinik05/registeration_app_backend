package com.home.prj.dto.response;

public record UserResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    String mobile,
    Boolean active) {
}
//Notice that password is intentionally excluded.