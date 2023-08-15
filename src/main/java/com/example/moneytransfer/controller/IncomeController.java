package com.example.moneytransfer.controller;

import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.IncomeDto;
import com.example.moneytransfer.payload.UserDto;
import com.example.moneytransfer.repository.IncomeRepository;
import com.example.moneytransfer.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/inCome")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @PostMapping
    public HttpEntity<?> income(@RequestBody IncomeDto incomeDto) {

        ApiResponse apiResponse = incomeService.income(incomeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {

        ApiResponse apiResponse = incomeService.get();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{incomeId}")
    public HttpEntity<?> getById(@PathVariable Integer incomeId) {

        ApiResponse apiResponse = incomeService.getById(incomeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
