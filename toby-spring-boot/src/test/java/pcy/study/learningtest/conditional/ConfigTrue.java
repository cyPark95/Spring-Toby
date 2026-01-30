package pcy.study.learningtest.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@BooleanConditional(true)
public class ConfigTrue {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
