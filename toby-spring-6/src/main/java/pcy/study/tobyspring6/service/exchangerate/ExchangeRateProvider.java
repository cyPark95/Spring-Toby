package pcy.study.tobyspring6.service.exchangerate;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String currency) throws IOException;
}
