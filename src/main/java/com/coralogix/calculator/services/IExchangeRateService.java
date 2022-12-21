package com.coralogix.calculator.services;

import com.coralogix.calculator.model.ExchangeRate;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IExchangeRateService {

    public Mono<ExchangeRate> getExchangeRate(String originCurrency, String finalCurrency);

    public Flux<ExchangeRate> getAllExchangeRate();

}
