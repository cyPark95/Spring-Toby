package pcy.study.tobyspring6.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pcy.study.tobyspring6.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DataSource dataSource;

    @Test
    void createOrder() {
        // when
        Order order = orderService.createOrder("0100", BigDecimal.ONE);

        // then
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        // given
        List<OrderRequest> orderRequests = List.of(
                new OrderRequest("0200", BigDecimal.ONE),
                new OrderRequest("0201", BigDecimal.TWO)
        );

        // when
        List<Order> orders = orderService.createOrders(orderRequests);

        // then
        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() {
        // given
        List<OrderRequest> orderRequests = List.of(
                new OrderRequest("0300", BigDecimal.ONE),
                new OrderRequest("0300", BigDecimal.TWO)
        );

        // when
        // then
        assertThatThrownBy(() -> orderService.createOrders(orderRequests))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient jdbcClient = JdbcClient.create(dataSource);
        Long result = jdbcClient.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
        assertThat(result).isEqualTo(0L);
    }
}
