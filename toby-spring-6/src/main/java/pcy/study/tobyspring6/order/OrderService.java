package pcy.study.tobyspring6.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        return new TransactionTemplate(transactionManager).execute(status -> {
            orderRepository.save(order);
            return order;
        });
    }
}
