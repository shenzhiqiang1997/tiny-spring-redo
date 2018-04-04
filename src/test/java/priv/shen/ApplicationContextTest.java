package priv.shen;

import org.junit.Test;
import priv.shen.context.ApplicationContext;
import priv.shen.context.XmlApplicationContext;

public class ApplicationContextTest {
    @Test
    public void test() throws Exception {
        //创建应用上下文
        ApplicationContext applicationContext=new XmlApplicationContext("tiny-ioc.xml");
        //通过应用上下文获取bean实例
        HelloService helloService= (HelloService) applicationContext.getBean("helloService");
        //使用bean实例
        helloService.hello();
    }
}
