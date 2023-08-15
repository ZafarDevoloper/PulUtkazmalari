package com.example.moneytransfer.payload;


import lombok.Data;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
