package pcy.study.tobyspring6.exchangerate;


import pcy.study.tobyspring6.payment.ExchangeRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CachedExchangeRateProvider implements ExchangeRateProvider {

    private final ExchangeRateProvider target;

    private final Map<String, BigDecimal> cachedExchangeRates = new HashMap<>();
    private final Map<String, LocalDateTime> cacheExpiryTimes = new HashMap<>();

    public CachedExchangeRateProvider(ExchangeRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        if(isValidCache(currency)) {
            return cachedExchangeRates.get(currency);
        }

        BigDecimal exchangeRate = target.getExchangeRate(currency);
        cachedExchangeRates.put(currency, exchangeRate);
        cacheExpiryTimes.put(currency, LocalDateTime.now().plusSeconds(3L));
        return exchangeRate;
    }

    private boolean isValidCache(String currency) {
        return cachedExchangeRates.containsKey(currency) && cacheExpiryTimes.get(currency).isAfter(LocalDateTime.now());
    }
}
