package pcy.study.learningtest.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {

    @Test
    void conditional() {
        // true
        new ApplicationContextRunner().withUserConfiguration(ConfigTrue.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(ConfigTrue.class);
                    assertThat(context).hasSingleBean(MyBean.class);
                });

        // false

        new ApplicationContextRunner().withUserConfiguration(ConfigFalse.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(ConfigFalse.class);
                    assertThat(context).doesNotHaveBean(MyBean.class);
                });
    }
}
