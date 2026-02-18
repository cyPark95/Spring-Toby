package pcy.study.tobyspring6.exchangerate;

import lombok.RequiredArgsConstructor;
import pcy.study.tobyspring6.api.ApiTemplate;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    private final ApiTemplate apiTemplate;

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExchangeRate(url);
    }
}
