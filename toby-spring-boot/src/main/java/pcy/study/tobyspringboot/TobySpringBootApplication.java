package pcy.study.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pcy.study.tobyspringboot.hello.controller.HelloController;
import pcy.study.tobyspringboot.hello.service.HelloService;
import pcy.study.tobyspringboot.hello.service.SimpleHelloService;

@Configuration
public class TobySpringBootApplication {

    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet(
                        "dispatcher-servlet",
                        new DispatcherServlet(this)
                ).addMapping("/*"));
                webServer.start();
            }
        };
        applicationContext.register(TobySpringBootApplication.class);
        applicationContext.refresh();
    }

}
