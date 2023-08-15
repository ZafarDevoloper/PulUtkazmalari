package com.example.moneytransfer.service;

import com.example.moneytransfer.entity.Card;
import com.example.moneytransfer.entity.Income;
import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.IncomeDto;
import com.example.moneytransfer.repository.CardRepository;
import com.example.moneytransfer.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;

    public ApiResponse income(IncomeDto incomeDto) {

        Income income = new Income();

        Optional<Card> fromCard = cardRepository.findById(incomeDto.getFromCardId());
        if (fromCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        income.setFromCardId(incomeDto.getFromCardId());

        Optional<Card> toCard = cardRepository.findById(incomeDto.getToCardId());
        if (toCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        income.setToCardId(incomeDto.getToCardId());
        income.setAmount(incomeDto.getAmount());

        Card fCard = fromCard.get();
        if (fCard.getBalance() < incomeDto.getAmount()) {
            return new ApiResponse("Insufficient funds", false);
        }
        fCard.setBalance(fCard.getBalance() - incomeDto.getAmount());
        cardRepository.save(fCard);

        Card tCard = toCard.get();
        tCard.setBalance(tCard.getBalance() + incomeDto.getAmount());
        cardRepository.save(tCard);

        incomeRepository.save(income);
        return new ApiResponse("Money received", true);
    }

    public ApiResponse get() {

        List<Income> incomes = incomeRepository.findAll();
        return new ApiResponse("Success", true, incomes);
    }

    public ApiResponse getById(Integer incomeId) {

        Optional<Income> optionalIncome = incomeRepository.findById(incomeId);
        if (optionalIncome.isEmpty()) {
            return new ApiResponse("Income not found", false);
        }
        return new ApiResponse("Success", true, optionalIncome);
    }
}
