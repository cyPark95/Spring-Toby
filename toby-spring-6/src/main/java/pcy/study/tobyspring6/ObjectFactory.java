package pcy.study.tobyspring6;

import pcy.study.tobyspring6.service.PaymentService;
import pcy.study.tobyspring6.service.exchangerate.ExchangeRateProvider;
import pcy.study.tobyspring6.service.exchangerate.WebApiExchangeRateProvider;

public class ObjectFactory {

    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider());
    }

    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider();
    }
}
