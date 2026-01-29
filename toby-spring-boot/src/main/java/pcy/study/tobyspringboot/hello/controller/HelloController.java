package pcy.study.tobyspringboot.hello.controller;

import lombok.RequiredArgsConstructor;
import pcy.study.tobyspringboot.hello.service.HelloService;

import java.util.Objects;

@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
