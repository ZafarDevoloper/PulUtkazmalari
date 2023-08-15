package com.example.moneytransfer.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserAuthDto {

    private String username;

    private String password;
}
