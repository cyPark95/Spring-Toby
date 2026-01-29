package pcy.study.learningtest;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void configuration() {
        MyConfiguration configration = new MyConfiguration();
        assertThat(configration.common()).isNotSameAs(configration.common());

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyConfiguration.class);
        applicationContext.refresh();

        BeanFirst first = applicationContext.getBean(BeanFirst.class);
        BeanSecond second = applicationContext.getBean(BeanSecond.class);

        assertThat(first.getCommon()).isSameAs(second.getCommon());
    }

    @Test
    void proxyCommonMethod() {
        MyConfigurationProxy proxy = new MyConfigurationProxy();

        BeanFirst first = proxy.beanFirst();
        BeanSecond second = proxy.beanSecond();

        assertThat(first.getCommon()).isSameAs(second.getCommon());
    }
}
