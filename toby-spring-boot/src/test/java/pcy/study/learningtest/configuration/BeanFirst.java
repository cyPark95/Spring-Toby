package pcy.study.learningtest.configuration;

public class BeanFirst {

    private final Common common;

    public BeanFirst(Common common) {
        this.common = common;
    }

    public Common getCommon() {
        return common;
    }
}
