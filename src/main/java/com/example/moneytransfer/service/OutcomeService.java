package com.example.moneytransfer.service;

import com.example.moneytransfer.entity.Card;
import com.example.moneytransfer.entity.Income;
import com.example.moneytransfer.entity.Outcome;
import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.OutcomeDto;
import com.example.moneytransfer.repository.CardRepository;
import com.example.moneytransfer.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;

    Double commission = 0.01;

    public ApiResponse outcome(OutcomeDto outcomeDto) {

        Outcome outcome = new Outcome();

        Optional<Card> fromCard = cardRepository.findById(outcomeDto.getFromCardId());
        if (fromCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        outcome.setFromCardId(outcomeDto.getFromCardId());

        Optional<Card> toCard = cardRepository.findById(outcomeDto.getToCardId());
        if (toCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        outcome.setToCardId(outcomeDto.getToCardId());
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setCommissionAmount(outcome.getAmount() * commission);

        Card fCard = fromCard.get();
        if (fCard.getBalance() < (outcome.getAmount() + outcome.getAmount() * commission)) {
            return new ApiResponse("Insufficient funds", false);
        }
        fCard.setBalance(fCard.getBalance() - (outcome.getAmount() + outcome.getAmount() * commission));
        cardRepository.save(fCard);

        Card tCard = toCard.get();
        tCard.setBalance(tCard.getBalance() + outcomeDto.getAmount());
        cardRepository.save(tCard);

        outcomeRepository.save(outcome);
        return new ApiResponse("Money sent", true);
    }

    public ApiResponse get() {

        List<Outcome> outcomes = outcomeRepository.findAll();
        return new ApiResponse("Success", true, outcomes);
    }

    public ApiResponse getById(Integer outcomeId) {

        Optional<Outcome> optionalOutcome = outcomeRepository.findById(outcomeId);
        if (optionalOutcome.isEmpty()) {
            return new ApiResponse("Outcome not found", false);
        }
        return new ApiResponse("Success", true, optionalOutcome);
    }
}
