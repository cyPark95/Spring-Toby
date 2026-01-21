package pcy.study.tobyspring6.order;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderServiceTxProxy implements OrderService {

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(transactionManager).execute(status ->
                target.createOrder(no, total)
        );
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> orderRequests) {
        return new TransactionTemplate(transactionManager).execute(status ->
                target.createOrders(orderRequests)
        );
    }
}
