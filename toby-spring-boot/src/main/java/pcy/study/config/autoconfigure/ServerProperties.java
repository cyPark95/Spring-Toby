package pcy.study.config.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import pcy.study.config.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "server")
public class ServerProperties {

    private int port;

    private Servlet servlet;

    @Getter
    @Setter
    public static class Servlet {

        private String contextPath;
    }
}
