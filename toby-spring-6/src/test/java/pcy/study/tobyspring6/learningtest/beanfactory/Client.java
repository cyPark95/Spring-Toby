package pcy.study.tobyspring6.learningtest.beanfactory;

public class Client {

    public static void main(String[] args) throws Throwable {
        DummyService service = new DummyService();
        TransactionHandler handler = new TransactionHandler(service, null);
        handler.invoke(service, DummyService.class.getMethod("save"), null);
    }
}
