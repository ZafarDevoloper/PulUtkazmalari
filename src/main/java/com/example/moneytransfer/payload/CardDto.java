package com.example.moneytransfer.payload;


import lombok.Data;

import java.util.Date;

@Data
public class CardDto {
    private String number;

    private String expiredDate;

    private Double balance;

}
