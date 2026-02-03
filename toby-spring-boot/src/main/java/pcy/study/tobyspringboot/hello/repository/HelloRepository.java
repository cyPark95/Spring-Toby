package pcy.study.tobyspringboot.hello.repository;

import pcy.study.tobyspringboot.hello.Hello;import java.util.Optional;public interface HelloRepository {

    Hello findHello(String name);

    void increaseCount(String name);

    default int countOf(String name) {
        Hello hello = findHello(name);
        return Optional.of(hello)
                .map(Hello::getCount)
                .orElse(0);
    }
}
