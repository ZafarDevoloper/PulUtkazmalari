package com.example.moneytransfer.controller;

import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.IncomeDto;
import com.example.moneytransfer.payload.OutcomeDto;
import com.example.moneytransfer.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/outCome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @PostMapping
    public HttpEntity<?> outcome(@RequestBody OutcomeDto outcomeDto) {

        ApiResponse apiResponse = outcomeService.outcome(outcomeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {

        ApiResponse apiResponse = outcomeService.get();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{outcomeId}")
    public HttpEntity<?> getById(@PathVariable Integer outcomeId) {

        ApiResponse apiResponse = outcomeService.getById(outcomeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
