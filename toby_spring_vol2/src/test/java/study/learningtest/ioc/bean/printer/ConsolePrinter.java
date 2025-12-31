package study.learningtest.ioc.bean.printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String message) {
        System.out.println( message);
    }
}
