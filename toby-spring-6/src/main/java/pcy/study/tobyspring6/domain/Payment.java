package pcy.study.tobyspring6.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
public class Payment {

    private final Long orderId;
    private final String currency;
    private final BigDecimal foreignCurrencyAmount;
    private final BigDecimal exchangeRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exchangeRate = exchangeRate;
        this.convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        this.validUntil = LocalDateTime.now().plusMinutes(30);
    }
}
