package com.example.moneytransfer.controller;

import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.CardDto;
import com.example.moneytransfer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> create(@RequestBody CardDto cardDto) {

        ApiResponse apiResponse = cardService.create(cardDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {

        ApiResponse apiResponse = cardService.get();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{cardId}")
    public HttpEntity<?> getById(@PathVariable Integer cardId) {

        ApiResponse apiResponse = cardService.getById(cardId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{cardId}")
    public HttpEntity<?> delete(@PathVariable Integer cardId) {

        ApiResponse apiResponse = cardService.delete(cardId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
