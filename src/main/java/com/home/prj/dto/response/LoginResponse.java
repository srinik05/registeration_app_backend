package com.home.prj.dto.response;

public record LoginResponse (
        String token,
        String tokenType,
        String message
){
}
