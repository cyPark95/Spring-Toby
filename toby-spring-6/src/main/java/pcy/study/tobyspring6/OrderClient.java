package pcy.study.tobyspring6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pcy.study.tobyspring6.order.Order;
import pcy.study.tobyspring6.order.OrderService;

import java.math.BigDecimal;

public class OrderClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);
        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}
