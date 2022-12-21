package com.coralogix.calculator.controller;

import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.services.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ExchangeRateController {

    @Autowired
    private IExchangeRateService exchangeRateService;


    @GetMapping(value = "/exchange-rate")
    public Mono<ExchangeRate> getExchangeRate(@RequestParam String originCurrency, @RequestParam String finalCurrency) {
        return exchangeRateService.getExchangeRate(originCurrency, finalCurrency);
    }

    @GetMapping(value = "/all-change-rate")
    public Flux<ExchangeRate> getAllExchangeRate() {
        return  exchangeRateService.getAllExchangeRate();
    }

}
