package pcy.study.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.tobyspring6.payment.PaymentService;
import pcy.study.tobyspring6.exchangerate.CachedExchangeRateProvider;
import pcy.study.tobyspring6.payment.ExchangeRateProvider;
import pcy.study.tobyspring6.exchangerate.WebApiExchangeRateProvider;

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
