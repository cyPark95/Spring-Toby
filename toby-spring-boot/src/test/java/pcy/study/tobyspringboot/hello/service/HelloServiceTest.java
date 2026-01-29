package pcy.study.tobyspringboot.hello.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void simpleHelloService() {
        // given
        HelloService helloService = new SimpleHelloService();

        // when
        String result = helloService.sayHello("Test");

        // then
        assertThat(result).isEqualTo("Hello Test");
    }

}
