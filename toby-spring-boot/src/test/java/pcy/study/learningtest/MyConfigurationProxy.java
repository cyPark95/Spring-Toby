package pcy.study.learningtest;

import org.springframework.context.annotation.Bean;

public class MyConfigurationProxy extends MyConfiguration {

    private Common common;

    @Bean
    public Common common() {
        if (common == null) {
            common = super.common();
        }

        return common;
    }
}
