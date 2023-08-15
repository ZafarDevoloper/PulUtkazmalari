package com.example.moneytransfer.repository;

import com.example.moneytransfer.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> getCardByUserId(Integer id);

}
