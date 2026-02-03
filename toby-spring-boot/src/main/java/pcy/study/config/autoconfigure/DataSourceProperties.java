package pcy.study.config.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import pcy.study.config.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;
}
