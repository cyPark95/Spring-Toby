package pcy.study.tobyspring6.exchangerate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RestTemplateExchangeProvider implements ExchangeRateProvider {

    private final RestTemplate restTemplate;

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return restTemplate.getForObject(url, ExchangeRateData.class).rates().get("KRW");
    }
}
