package com.coralogix.calculator.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exchangerates")
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "origin_currency")
    private String originCurrency;
    @Column(name = "final_currency")
    private String finalCurrency;
    @Column(name = "fecha")
    private String date;
    @Column(name = "valor")
    private String value;


    public ExchangeRate(String originCurrency, String finalCurrency, String date, String value) {
        this.originCurrency = originCurrency;
        this.finalCurrency = finalCurrency;
        this.date = date;
        this.value = value;
    }
    public ExchangeRate() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public String getFinalCurrency() {
        return finalCurrency;
    }

    public void setFinalCurrency(String finalCurrency) {
        this.finalCurrency = finalCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
