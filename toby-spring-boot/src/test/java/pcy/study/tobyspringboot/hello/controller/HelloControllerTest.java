package pcy.study.tobyspringboot.hello.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {

    @Test
    void helloController() {
        // given
        HelloController helloController = new HelloController(name -> name);

        // when
        String result = helloController.hello("Test");

        // then
        assertThat(result).isEqualTo("Test");
    }

    @Test
    void failsElloController() {
        // given
        HelloController helloController = new HelloController(name -> name);

        // when
        // then
        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
