package pcy.study.tobyspringboot.hello.controller;

import org.junit.jupiter.api.Test;
import pcy.study.tobyspringboot.hello.service.HelloService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {

    private final HelloService helloServiceStub = new HelloService() {
        @Override
        public String sayHello(String name) {
            return name;
        }

        @Override
        public int countOf(String name) {
            return 0;
        }
    };

    @Test
    void helloController() {
        // given
        HelloController helloController = new HelloController(helloServiceStub);

        // when
        String result = helloController.hello("Test");

        // then
        assertThat(result).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        // given
        HelloController helloController = new HelloController(helloServiceStub);

        // when
        // then
        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
