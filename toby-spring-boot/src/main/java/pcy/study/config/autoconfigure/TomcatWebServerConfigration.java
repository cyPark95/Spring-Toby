package pcy.study.config.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pcy.study.config.AutoConfiguration;
import pcy.study.config.ConditionalOnClass;

@AutoConfiguration
@ConditionalOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfigration {

    @Value("${server.servlet.context-path:/")
    private String contextPath;

    @Value("${server.port:8080}")
    private int port;

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(this.contextPath);
        serverFactory.setPort(this.port);
        return serverFactory;
    }
}
