package pcy.study.tobyspring6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import pcy.study.tobyspring6.data.OrderRepository;
import pcy.study.tobyspring6.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        JpaTransactionManager transactionManager = applicationContext.getBean(JpaTransactionManager.class);
        OrderRepository orderRepository = applicationContext.getBean(OrderRepository.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                orderRepository.save(order);

                System.out.println(order);

                Order invalidOrder = new Order("100", BigDecimal.ZERO);
                orderRepository.save(invalidOrder);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            System.out.println("주문 번호 중복 복구 작업");
        }
    }
}
