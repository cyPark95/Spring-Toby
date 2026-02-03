package pcy.study.tobyspringboot.hello.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.tobyspringboot.HelloBootTest;
import pcy.study.tobyspringboot.hello.Hello;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
@Transactional
class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        // when
        Hello hello = helloRepository.findHello("toby");

        // then
        assertThat(hello).isNull();
    }

    @Test
    void increaseCount() {
        // when
        helloRepository.increaseCount("toby");

        // then
        int count = helloRepository.countOf("toby");
        assertThat(count).isEqualTo(1);
    }
}
