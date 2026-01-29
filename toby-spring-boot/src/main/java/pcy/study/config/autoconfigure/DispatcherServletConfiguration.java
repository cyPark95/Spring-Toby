package pcy.study.config.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import pcy.study.config.AutoConfiguration;

@AutoConfiguration
public class DispatcherServletConfiguration {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
