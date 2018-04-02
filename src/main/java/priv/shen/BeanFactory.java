package priv.shen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean工厂 定义获取、注册bean的方法
 */
public class BeanFactory {
    //一个线程安全的map来存放 beanName-beanDefinition键值对
    private Map<String, BeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>();

    //获取bean
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    //注册bean 即创建bean的实例
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}