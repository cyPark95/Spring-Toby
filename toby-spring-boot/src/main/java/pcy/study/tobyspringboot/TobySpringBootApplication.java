package pcy.study.tobyspringboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import pcy.study.tobyspringboot.hello.controller.HelloController;
import pcy.study.tobyspringboot.hello.service.SimpleHelloService;

import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet(
                "front-controller",
                new HttpServlet() {
                    @Override
                    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
                        // 인증, 보안, 다국어, 공통 기능 처리
                        // ...

                        if (request.getRequestURI().equals("/hello") && request.getMethod().equals(HttpMethod.GET.name())) {
                            String name = request.getParameter("name");

                            HelloController helloController = applicationContext.getBean(HelloController.class);
                            String message = helloController.hello(name);

                            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                            response.getWriter().print(message);
                        } else {
                            response.setStatus(HttpStatus.NOT_FOUND.value());
                        }
                    }
                }).addMapping("/*"));
        webServer.start();
    }

}
