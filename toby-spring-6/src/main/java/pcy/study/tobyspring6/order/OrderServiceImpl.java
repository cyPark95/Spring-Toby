package pcy.study.tobyspring6.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> orderRequests) {
        return orderRequests.stream()
                .map(request -> createOrder(request.no(), request.total()))
                .toList();
    }
}
