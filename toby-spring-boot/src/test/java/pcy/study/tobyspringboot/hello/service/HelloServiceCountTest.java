package pcy.study.tobyspringboot.hello.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobyspringboot.HelloBootTest;
import pcy.study.tobyspringboot.hello.repository.HelloRepository;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
@Transactional
public class HelloServiceCountTest {

    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() {
        // when
        helloService.sayHello("Toby");

        // then
        int count = helloRepository.countOf("Toby");
        assertThat(count).isEqualTo(1);
    }
}
