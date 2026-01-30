package pcy.study.config.autoconfigure;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pcy.study.config.AutoConfiguration;
import pcy.study.config.ConditionalOnClass;

@AutoConfiguration
@ConditionalOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfigration {

    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
