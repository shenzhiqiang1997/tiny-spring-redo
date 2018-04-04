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
    public Object getBean(String name) throws Exception {

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition==null)
            throw new IllegalArgumentException("error:bean named "+name+"is not defined");

        //实现懒加载 如果不存在bean实例再加载
        Object bean=beanDefinition.getBean();

        if (bean==null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition) throws Exception{
        //先创建bean再将bean放置到map中
        beanDefinitionMap.put(name,beanDefinition);
    }

    //不选择懒加载 而是提前创建并注入所有需要注入的bean
    public void preInstantiateSingletons() throws Exception {
        for (String name:
             beanDefinitionMap.keySet()) {
            getBean(name);
        }
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
