package pcy.study.tobyspring6;

import pcy.study.tobyspring6.domain.Payment;
import pcy.study.tobyspring6.service.PaymentService;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws Exception {
        ObjectFactory factory = new ObjectFactory();
        PaymentService paymentService = factory.paymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
