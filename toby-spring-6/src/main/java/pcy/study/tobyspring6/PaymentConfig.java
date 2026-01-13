package pcy.study.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.tobyspring6.api.ApiTemplate;
import pcy.study.tobyspring6.exchangerate.CachedExchangeRateProvider;
import pcy.study.tobyspring6.exchangerate.WebApiExchangeRateProvider;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;
import pcy.study.tobyspring6.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(currencyExchangeRateProvider(), clock());
    }

    @Bean
    public ExchangeRateProvider currencyExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
    }
}
