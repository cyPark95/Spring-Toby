package pcy.study.tobyspringboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            HelloController helloController = new HelloController();

            servletContext.addServlet(
                    "front-controller",
                    new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
                            // 인증, 보안, 다국어, 공통 기능 처리
                            // ...

                            if (request.getRequestURI().equals("/hello") && request.getMethod().equals(HttpMethod.GET.name())) {
                                String name = request.getParameter("name");

                                String message = helloController.hello(name);

                                response.setStatus(HttpStatus.OK.value());
                                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                response.getWriter().print(message);
                            } else if (request.getRequestURI().equals("/user")) {
                                //
                            } else {
                                response.setStatus(HttpStatus.NOT_FOUND.value());
                            }
                        }
                    }).addMapping("/*");
        });
        webServer.start();
    }

}
