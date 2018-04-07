package priv.shen.context;

import org.junit.Test;
import priv.shen.HelloServiceImpl;

public class ApplicationContextTest {
    @Test
    public void test() throws Exception {
        //创建应用上下文
        ApplicationContext applicationContext=new XmlApplicationContext("tiny-ioc.xml");
        //通过应用上下文获取bean实例
        HelloServiceImpl helloService= (HelloServiceImpl) applicationContext.getBean("helloService");
        //使用bean实例
        helloService.hello();
    }

    @Test
    public void testWithBeanPostProcessor() throws Exception {
        //创建应用上下文
        ApplicationContext applicationContext=new XmlApplicationContext("tiny-ioc-post-processor.xml");
        //通过应用上下文获取bean实例
        HelloServiceImpl helloService= (HelloServiceImpl) applicationContext.getBean("helloService");
        //使用bean实例
        helloService.hello();
    }

}
