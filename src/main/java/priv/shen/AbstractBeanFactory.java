package priv.shen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现了获取bean与注册BeanDefinition
 * 定义了创建bean的模板方法
 */
public abstract class AbstractBeanFactory implements BeanFactory{
    //存放注册过的BeanDefinition
    private Map<String,BeanDefinition> beanDefinitionMap
            = new ConcurrentHashMap<>();

    //获取bean
    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    //注册BeanDefinition
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean=doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name,beanDefinition);
    }

    //定义创建bean的模板方法
    public abstract Object doCreateBean(BeanDefinition beanDefinition);

}
