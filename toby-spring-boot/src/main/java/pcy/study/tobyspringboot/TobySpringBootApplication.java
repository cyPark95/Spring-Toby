package pcy.study.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pcy.study.tobyspringboot.hello.controller.HelloController;
import pcy.study.tobyspringboot.hello.service.SimpleHelloService;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet(
                "dispatcher-servlet",
                new DispatcherServlet(applicationContext)
        ).addMapping("/*"));
        webServer.start();
    }

}
