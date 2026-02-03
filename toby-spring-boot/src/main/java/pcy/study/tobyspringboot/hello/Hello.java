package pcy.study.tobyspringboot.hello;

import lombok.Getter;

@Getter
public class Hello {

    private final String name;

    private final int count;

    public Hello(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
