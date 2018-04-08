package priv.shen.aop;

import org.junit.Test;
import priv.shen.HelloService;
import priv.shen.context.ApplicationContext;
import priv.shen.context.XmlApplicationContext;

public class AutoProxyCreatorTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext=new XmlApplicationContext("tiny-ioc.xml");
        HelloService helloService= (HelloService) applicationContext.getBean("helloService");
        helloService.hello();
    }
}
