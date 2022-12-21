package com.coralogix.calculator.services;

import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.repository.IExchangeRateDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExchangeRateServiceImpl implements IExchangeRateService {

  @Autowired WebClient webClient;

  @Autowired private IExchangeRateDao exchangeRateDao;

  @Override
  public Mono<ExchangeRate> getExchangeRate(String originCurrency, String finalCurrency) {
    List<ExchangeRate> list =
        exchangeRateDao.findExchangeRateByCurrencyBaseAnfFinal(originCurrency, finalCurrency);

    if (list != null && list.size() > 0) {

      return Mono.just(list.get(0));
    }
    return
        webClient
            .get()
            .uri(
                uriBuilder ->
                    uriBuilder
                        .path("/fixer/latest/")
                        .queryParam("base", originCurrency)
                        .queryParam("symbols", finalCurrency)
                        .build())
            .retrieve()
            .bodyToMono(String.class)
            .flatMap(
                r -> {
                  JSONObject json = new JSONObject(r);
                  String currency  = json.getJSONObject("rates").getBigDecimal(finalCurrency).toString();
                  return Mono.just(exchangeRateDao.save(
                          new ExchangeRate(
                                  originCurrency,
                                  finalCurrency,
                                  new Date().toString(),
                                  currency)));
                });
  }

  @Override
  public Flux<ExchangeRate> getAllExchangeRate() {
    return Flux.fromIterable(exchangeRateDao.findAll());
  }
}
