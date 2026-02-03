package pcy.study.tobyspringboot.hello.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.tobyspringboot.hello.repository.HelloRepository;

@Service
@RequiredArgsConstructor
public class SimpleHelloService implements HelloService {

    private final HelloRepository helloRepository;

    @Override
    public String sayHello(String name) {
        helloRepository.increaseCount(name);
        return "Hello " + name;
    }
}
