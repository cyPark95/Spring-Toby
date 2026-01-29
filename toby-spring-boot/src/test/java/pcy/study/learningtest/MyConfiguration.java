package pcy.study.learningtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public Common common() {
        return new Common();
    }

    @Bean
    public BeanFirst beanFirst() {
        return new BeanFirst(common());
    }

    @Bean
    public BeanSecond beanSecond() {
        return new BeanSecond(common());
    }
}
