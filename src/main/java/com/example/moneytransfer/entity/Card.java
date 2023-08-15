package com.example.moneytransfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String expiredDate;

    @Column(nullable = false)
    private Double balance;

    private boolean active;

}
