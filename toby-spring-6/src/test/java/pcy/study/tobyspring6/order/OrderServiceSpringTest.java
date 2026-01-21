package pcy.study.tobyspring6.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pcy.study.tobyspring6.OrderConfig;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder() {
        // when
        Order order = orderService.createOrder("0100", BigDecimal.ONE);

        // then
        assertThat(order.getId()).isGreaterThan(0);
    }
}
