package priv.shen.beans;

import priv.shen.HelloServiceImpl;
import priv.shen.beans.beanDefinitionReader.BeanDefinitionReader;
import priv.shen.beans.beanDefinitionReader.XmlBeanDefinitionReader;
import priv.shen.beans.factory.AbstractBeanFactory;
import priv.shen.beans.factory.AutoCapableBeanFactory;
import org.junit.Test;
import priv.shen.beans.beanDefinition.BeanDefinition;
import priv.shen.beans.resource.ResourceLoader;
import priv.shen.beans.resource.UrlResourceLoader;

import java.util.Map;

public class BeanFactoryTest {
    @Test
    public void test() throws Exception{
        //创建bean工厂
        AbstractBeanFactory factory=new AutoCapableBeanFactory();

        //创建资源加载器
        ResourceLoader resourceLoader=new UrlResourceLoader();

        //创建bean定义加载器
        BeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(resourceLoader);

        //加载bean定义
        beanDefinitionReader.loadBeanDefinitions("tiny-ioc.xml");

        //注册bean
        Map<String,BeanDefinition> register = ((XmlBeanDefinitionReader) beanDefinitionReader).getRegister();
        for (Map.Entry<String,BeanDefinition> entry:
             register.entrySet()) {
            factory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }

        factory.preInstantiateSingletons();


        //获取bean
        HelloServiceImpl helloService= (HelloServiceImpl) factory.getBean("helloService");
        helloService.hello();
    }
}
