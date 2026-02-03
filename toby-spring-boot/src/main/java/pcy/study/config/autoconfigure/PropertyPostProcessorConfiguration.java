package pcy.study.config.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import pcy.study.config.AutoConfiguration;
import pcy.study.config.ConfigurationProperties;

import java.util.Map;

@AutoConfiguration
public class PropertyPostProcessorConfiguration {

    @Bean
    public BeanPostProcessor propertyPostProcessor(Environment environment) {
        return new BeanPostProcessor() {

            @Nullable
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                ConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), ConfigurationProperties.class);
                if (annotation == null) {
                    return bean;
                }

                Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attributes.get("prefix");

                return Binder.get(environment).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
