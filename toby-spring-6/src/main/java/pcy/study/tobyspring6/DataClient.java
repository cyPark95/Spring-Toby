package pcy.study.tobyspring6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pcy.study.tobyspring6.data.OrderRepository;
import pcy.study.tobyspring6.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = applicationContext.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);

        System.out.println(order);
    }
}
