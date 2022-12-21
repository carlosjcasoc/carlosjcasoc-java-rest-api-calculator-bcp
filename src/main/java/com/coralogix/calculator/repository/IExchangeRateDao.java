package com.coralogix.calculator.repository;

import com.coralogix.calculator.model.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExchangeRateDao extends CrudRepository<ExchangeRate, Long> {

    @Query(" select er FROM ExchangeRate er WHERE er.originCurrency = :originCurrency and er.finalCurrency = :finalCurrency ")
    List<ExchangeRate> findExchangeRateByCurrencyBaseAnfFinal(String originCurrency, String finalCurrency);
}
