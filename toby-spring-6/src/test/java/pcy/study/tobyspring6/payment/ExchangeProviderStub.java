package pcy.study.tobyspring6.payment;

import java.math.BigDecimal;

public class ExchangeProviderStub implements ExchangeRateProvider {

    private BigDecimal exchangeRate;

    public ExchangeProviderStub(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        return exchangeRate;
    }
}
