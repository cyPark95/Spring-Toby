package pcy.study.tobyspring6.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pcy.study.tobyspring6.domain.ExchangeRateData;
import pcy.study.tobyspring6.domain.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exchangeRate = getExchangeRate(currency);
        return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate);
    }

    private BigDecimal getExchangeRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData data = mapper.readValue(response, ExchangeRateData.class);
        return data.rates().get("KRW");
    }

    public static void main(String[] args) throws Exception {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
