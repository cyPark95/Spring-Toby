package pcy.study.tobyspring6.exchangerate;

import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원하지 않는 통화 입니다.");
    }
}
