package pcy.study.tobyspring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor defaultApiExecutor;
    private final ExchangeExtractor defaultExchangeExtractor;

    public ApiTemplate() {
        this.defaultApiExecutor = new HttpClientApiExchangeExecutor();
        this.defaultExchangeExtractor = new ErApiExchangeExtractor();
    }

    public ApiTemplate(ApiExecutor defaultApiExecutor, ExchangeExtractor defaultExchangeExtractor) {
        this.defaultApiExecutor = defaultApiExecutor;
        this.defaultExchangeExtractor = defaultExchangeExtractor;
    }

    public BigDecimal getExchangeRate(String url) {
        return getExchangeRate(url, this.defaultApiExecutor, this.defaultExchangeExtractor);
    }

    public BigDecimal getExchangeRate(String url, ApiExecutor apiExecutor) {
        return getExchangeRate(url, apiExecutor, this.defaultExchangeExtractor);
    }

    public BigDecimal getExchangeRate(String url, ExchangeExtractor exchangeExtractor) {
        return getExchangeRate(url, this.defaultApiExecutor, exchangeExtractor);
    }

    public BigDecimal getExchangeRate(String url, ApiExecutor apiExecutor, ExchangeExtractor exchangeExtractor) {
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
