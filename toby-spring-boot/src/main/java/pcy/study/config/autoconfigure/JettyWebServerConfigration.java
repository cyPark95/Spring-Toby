package pcy.study.config.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pcy.study.config.AutoConfiguration;
import pcy.study.config.ConditionalOnClass;

@AutoConfiguration
@ConditionalOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfigration {

    @Bean("jettyWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
