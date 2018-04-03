package priv.shen;

import priv.shen.factory.AutoCapableBeanFactory;
import priv.shen.factory.BeanFactory;
import org.junit.Test;
import priv.shen.beanDefinition.BeanDefinition;
import priv.shen.beanDefinition.PropertyValue;
import priv.shen.beanDefinition.PropertyValues;

public class BeanFactoryTest {
    @Test
    public void test() throws Exception{
        //创建bean工厂
        BeanFactory factory=new AutoCapableBeanFactory();

        //创建bean定义
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName("priv.shen.HelloService");

        //创建bean属性
        PropertyValues propertyValues=new PropertyValues();
        //加入属性
        propertyValues.addPropertyValue(new PropertyValue("text","Hello World!"));
        //将bean属性绑定到bean定义中
        beanDefinition.setPropertyValues(propertyValues);

        //注册bean
        factory.registerBeanDefinition("helloService",beanDefinition);

        //获取bean
        HelloService helloService= (HelloService) factory.getBean("helloService");
        helloService.hello();
    }
}
