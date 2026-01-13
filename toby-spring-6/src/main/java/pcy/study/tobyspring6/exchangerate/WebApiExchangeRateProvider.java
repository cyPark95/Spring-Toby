package pcy.study.tobyspring6.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import pcy.study.tobyspring6.api.ApiExecutor;
import pcy.study.tobyspring6.api.ErApiExchangeExtractor;
import pcy.study.tobyspring6.api.ExchangeExtractor;
import pcy.study.tobyspring6.api.SimpleApiExecutor;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExchangeRate(url, new SimpleApiExecutor(), new ErApiExchangeExtractor());
    }

    private BigDecimal runApiForExchangeRate(String url, ApiExecutor apiExecutor, ExchangeExtractor exchangeExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exchangeExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
