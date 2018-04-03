package priv.shen;

import priv.shen.beanDefinitionReader.BeanDefinitionReader;
import priv.shen.beanDefinitionReader.XmlBeanDefinitionReader;
import priv.shen.factory.AutoCapableBeanFactory;
import priv.shen.factory.BeanFactory;
import org.junit.Test;
import priv.shen.beanDefinition.BeanDefinition;
import priv.shen.beanDefinition.PropertyValue;
import priv.shen.beanDefinition.PropertyValues;
import priv.shen.resource.ResourceLoader;
import priv.shen.resource.UrlResourceLoader;

import java.util.Map;

public class BeanFactoryTest {
    @Test
    public void test() throws Exception{
        //创建bean工厂
        BeanFactory factory=new AutoCapableBeanFactory();

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

        //获取bean
        HelloService helloService= (HelloService) factory.getBean("helloService");
        helloService.hello();
    }
}
