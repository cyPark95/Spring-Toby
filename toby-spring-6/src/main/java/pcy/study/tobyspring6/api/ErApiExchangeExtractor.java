package pcy.study.tobyspring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pcy.study.tobyspring6.exchangerate.ExchangeRateData;

import java.math.BigDecimal;

public class ErApiExchangeExtractor implements ExchangeExtractor {

    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData data = mapper.readValue(response, ExchangeRateData.class);
        return data.rates().get("KRW");
    }
}
