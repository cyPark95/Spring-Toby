package pcy.study.learningtest.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FalseConditional
public class ConfigFalse {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
