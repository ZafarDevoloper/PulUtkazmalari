package com.example.moneytransfer.service;

import com.example.moneytransfer.entity.Card;
import com.example.moneytransfer.entity.User;
import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.CardDto;
import com.example.moneytransfer.repository.CardRepository;
import com.example.moneytransfer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponse create(CardDto cardDto) {

        Card card = new Card();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        card.setUser(user);
        card.setNumber(cardDto.getNumber());
        card.setExpiredDate(cardDto.getExpiredDate());
        card.setBalance(cardDto.getBalance());
        card.setActive(true);
        cardRepository.save(card);

        return new ApiResponse("Card created", true);
    }

    public ApiResponse get() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Card> cards = cardRepository.getCardByUserId(user.getId());
        return new ApiResponse("Success", true, cards);

    }

    public ApiResponse getById(Integer cardId) {

        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        return new ApiResponse("Success", true, optionalCard);
    }

    public ApiResponse delete(Integer cardId) {

        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty()) {
            return new ApiResponse("Card not found", false);
        }
        cardRepository.deleteById(cardId);
        return new ApiResponse("card deleted", true);
    }
}
