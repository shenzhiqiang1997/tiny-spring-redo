package priv.shen;

import org.junit.Test;

public class BeanFactoryTest {
    @Test
    public void test(){
        //创建bean工厂
        BeanFactory factory=new AutoCapableBeanFactory();
        //创建类信息对象
        BeanDefinition beanDefinition=new BeanDefinition();
        //设置bean类的全限定名
        beanDefinition.setBeanClassName("priv.shen.HelloService");
        //注册bean
        factory.registerBeanDefinition("helloService",beanDefinition);
        //获取bean
        HelloService helloService= (HelloService) factory.getBean("helloService");
        helloService.helloWorld();
    }
}
