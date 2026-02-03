package pcy.study.tobyspringboot.hello.service;

import org.junit.jupiter.api.Test;
import pcy.study.tobyspringboot.hello.Hello;
import pcy.study.tobyspringboot.hello.repository.HelloRepository;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    private HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void simpleHelloService() {
        // given
        HelloService helloService = new SimpleHelloService(helloRepositoryStub);

        // when
        String result = helloService.sayHello("Test");

        // then
        assertThat(result).isEqualTo("Hello Test");
    }
}
