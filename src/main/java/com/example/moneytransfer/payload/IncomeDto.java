package com.example.moneytransfer.payload;

import lombok.Data;

import java.util.Date;

@Data
public class IncomeDto {

    private Integer fromCardId;

    private Integer toCardId;

    private Double amount;
}
