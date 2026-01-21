package pcy.study.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import pcy.study.tobyspring6.data.JdbcOrderRepository;
import pcy.study.tobyspring6.order.OrderRepository;
import pcy.study.tobyspring6.order.OrderService;
import pcy.study.tobyspring6.order.OrderServiceImpl;
import pcy.study.tobyspring6.order.OrderServiceTxProxy;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(
            OrderRepository orderRepository,
            PlatformTransactionManager transactionManager
    ) {
        return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
