package com.example.demo.dto;

import lombok.Getter;

@Getter
public class MyPageResponseDto {
    private String name;
    private String email;
    private String phone;
    private String clearId;

    public MyPageResponseDto(String name, String email, String phone, String clearId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clearId = clearId;
    }
}