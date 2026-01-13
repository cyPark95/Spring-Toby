package pcy.study.tobyspring6.exchangerate;

import pcy.study.tobyspring6.api.ApiTemplate;
import pcy.study.tobyspring6.api.ErApiExchangeExtractor;
import pcy.study.tobyspring6.api.HttpClientApiExchangeExecutor;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    private final ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExchangeRate(url, new HttpClientApiExchangeExecutor(), new ErApiExchangeExtractor());
    }
}
