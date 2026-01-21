package pcy.study.tobyspring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pcy.study.tobyspring6.order.Order;
import pcy.study.tobyspring6.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override public void save(Order order) {
        entityManager.persist(order);
    }
}
