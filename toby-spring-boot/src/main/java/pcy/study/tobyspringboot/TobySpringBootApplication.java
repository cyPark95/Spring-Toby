package pcy.study.tobyspringboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet(
                "hello",
                new HttpServlet() {
                    @Override
                    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
                        String name = request.getParameter("name");

                        response.setStatus(HttpStatus.OK.value());
                        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        response.getWriter().print("Hello " + name);
                    }
                }).addMapping("/hello"));
        webServer.start();
    }

}
