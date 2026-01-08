package pcy.study.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.tobyspring6.service.PaymentService;
import pcy.study.tobyspring6.service.exchangerate.CachedExchangeRateProvider;
import pcy.study.tobyspring6.service.exchangerate.ExchangeRateProvider;
import pcy.study.tobyspring6.service.exchangerate.WebApiExchangeRateProvider;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(currencyExchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider currencyExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider();
    }
}
