package pcy.study.config.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pcy.study.config.AutoConfiguration;
import pcy.study.config.ConditionalOnClass;
import pcy.study.config.EnableConfigurationProperties;

@AutoConfiguration
@ConditionalOnClass("org.apache.catalina.startup.Tomcat")
@EnableConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfigration {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(properties.getServlet().getContextPath());
        serverFactory.setPort(properties.getPort());
        return serverFactory;
    }
}
