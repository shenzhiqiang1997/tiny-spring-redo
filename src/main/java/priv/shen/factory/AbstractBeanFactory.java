package priv.shen.factory;

import priv.shen.beanDefinition.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象bean工厂 实现了beanFactory接口的获得bean与注册bean的规范
 * 定义了存放已注册bean的map
 * 定义了创建bean实例的模板方法
 */
public abstract class AbstractBeanFactory implements BeanFactory{
    private Map<String,BeanDefinition> beanDefinitionMap
            = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition) throws Exception{
        //先创建bean再将bean放置到map中
        Object bean=doCreateBean(beanDefinition);
        beanDefinitionMap.put(name,beanDefinition);
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
