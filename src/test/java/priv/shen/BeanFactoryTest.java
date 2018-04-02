package priv.shen;

import org.junit.Test;

public class BeanFactoryTest {
    @Test
    public void test(){
        //创建bean工厂
        BeanFactory factory=new BeanFactory();
        //创建bean
        BeanDefinition beanDefinition=new BeanDefinition(new HelloService());
        //注册bean
        factory.registerBeanDefinition("helloService",beanDefinition);
        //获取bean
        ((HelloService)factory.getBean("helloService")).helloWorld();
    }
}
