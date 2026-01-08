package pcy.study.tobyspring6.service;

import pcy.study.tobyspring6.domain.Payment;
import pcy.study.tobyspring6.service.exchangerate.WebApiExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class PaymentService {

    private final WebApiExchangeRateProvider exchangeRateProvider;

    public PaymentService() {
        this.exchangeRateProvider = new WebApiExchangeRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency);
        return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate);
    }
}
