package pcy.study.tobyspring6.service;

import pcy.study.tobyspring6.domain.Payment;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class AbstractPaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exchangeRate = getExchangeRate(currency);
        return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate);
    }

    abstract BigDecimal getExchangeRate(String currency) throws IOException;
}
