package study.learningtest.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import study.learningtest.ioc.bean.Hello;
import study.learningtest.ioc.bean.printer.Printer;
import study.learningtest.ioc.bean.printer.StringPrinter;

import static org.junit.jupiter.api.Assertions.*;

public class IoCTest {

    @Test
    void registerBean() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello1", Hello.class);

        Hello hello1 = applicationContext.getBean("hello1", Hello.class);
        assertNotNull(hello1);

        RootBeanDefinition helloDefinition = new RootBeanDefinition(Hello.class);
        helloDefinition.getPropertyValues().addPropertyValue("name", "spring");
        applicationContext.registerBeanDefinition("hello2", helloDefinition);

        Hello hello2 = applicationContext.getBean("hello2", Hello.class);
        assertNotEquals(hello1, hello2);

        assertEquals(2, applicationContext.getBeanFactory().getBeanDefinitionCount());
    }

    @Test
    void registerBeanWithDependency() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();

        applicationContext.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        RootBeanDefinition hellDefinition = new RootBeanDefinition(Hello.class);
        hellDefinition.getPropertyValues().addPropertyValue("name", "Spring");
        hellDefinition.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
        applicationContext.registerBeanDefinition("hello", hellDefinition);

        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    void genericApplicationContext() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("learningtest/ioc/genericApplicationContext.xml");

        applicationContext.refresh();

        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    void genericXmlApplicationContext() {
        GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("learningtest/ioc/genericApplicationContext.xml");

        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", applicationContext.getBean("printer").toString());
    }

    @Test
    void aa() {
        GenericXmlApplicationContext parent = new GenericXmlApplicationContext("learningtest/ioc/parentContext.xml");
        GenericApplicationContext child = new GenericApplicationContext(parent);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        reader.loadBeanDefinitions("learningtest/ioc/childContext.xml");
        child.refresh();

        Printer printer = child.getBean("printer", Printer.class);
        assertNotNull(printer);

        Hello hello = child.getBean("hello", Hello.class);
        assertNotNull(hello);

        hello.print();
        assertEquals("Hello Child", printer.toString());
    }
}
