package priv.shen.factory;

import priv.shen.beanDefinition.BeanDefinition;

/**
 * bean工厂 定义获得bean和注册bean的规范
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
