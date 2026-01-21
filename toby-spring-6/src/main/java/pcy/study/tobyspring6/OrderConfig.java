package pcy.study.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import pcy.study.tobyspring6.data.JdbcOrderRepository;
import pcy.study.tobyspring6.order.OrderRepository;
import pcy.study.tobyspring6.order.OrderService;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(
            OrderRepository orderRepository,
            PlatformTransactionManager transactionManager
    ) {
        return new OrderService(orderRepository, transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
