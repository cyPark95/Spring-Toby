package pcy.study.tobyspring6.service;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchangeRatePaymentService extends AbstractPaymentService {

    @Override
    BigDecimal getExchangeRate(String currency) throws IOException {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원하지 않는 통화 입니다.");
    }
}
