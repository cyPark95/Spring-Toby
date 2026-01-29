package pcy.study.config;

import org.springframework.context.annotation.Import;
import pcy.study.config.autoconfig.DispatcherServletConfiguration;
import pcy.study.config.autoconfig.TomcatWebServerConfigration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServletConfiguration.class, TomcatWebServerConfigration.class})
public @interface EnableAutoConfiguration {
}
