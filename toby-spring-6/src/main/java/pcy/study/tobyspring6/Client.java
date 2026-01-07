package pcy.study.tobyspring6;

import pcy.study.tobyspring6.domain.Payment;
import pcy.study.tobyspring6.service.AbstractPaymentService;
import pcy.study.tobyspring6.service.WebApiExchangeRatePaymentService;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws Exception {
        AbstractPaymentService paymentService = new WebApiExchangeRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
