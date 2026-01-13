package pcy.study.tobyspring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExchangeExtractor {

    BigDecimal extract(String response) throws JsonProcessingException;
}
