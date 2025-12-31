package study.learningtest.ioc.bean.printer;

public class StringPrinter implements Printer {

    private final StringBuffer buffer = new StringBuffer();

    @Override
    public void print(String message) {
        this.buffer.append(message);
    }

    public String toString() {
        return this.buffer.toString();
    }
}
