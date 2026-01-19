package pcy.study.tobyspring6.learningtest.beanfactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.lang.reflect.Proxy;

public class DummyFactoryBean implements FactoryBean<DummyService> {

    @Nullable
    @Override
    public DummyService getObject() {
        DummyService service = new DummyService();
        TransactionHandler handler = new TransactionHandler(service, null);

        return (DummyService) Proxy.newProxyInstance(
                DummyService.class.getClassLoader(),
                new Class[]{DummyService.class},
                handler
        );
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return DummyService.class;
    }
}
