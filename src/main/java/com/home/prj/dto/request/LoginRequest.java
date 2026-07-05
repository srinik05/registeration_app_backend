package com.home.prj.dto.request;

public record LoginRequest(
    String email,
    String password
) {
}
