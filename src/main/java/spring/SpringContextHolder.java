package spring;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextHolder {
    private final static AbstractXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    public static AbstractXmlApplicationContext getContext() {
        return context;
    }
}
