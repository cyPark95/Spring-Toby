package pcy.study.tobyspring6.learningtest.beanfactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

    private final Object target;
    private final EntityManager entityManager;

    public TransactionHandler(Object target, EntityManager entityManager) {
        this.target = target;
        this.entityManager = entityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Object result = method.invoke(target, args);

            transaction.commit();

            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
