package priv.shen.beans.factory;

import priv.shen.beans.BeanPostProcessor;
import priv.shen.beans.beanDefinition.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
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
    private List<BeanPostProcessor> beanPostProcessors
            = new ArrayList<>();
    @Override
    public Object getBean(String name) throws Exception {

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition==null)
            throw new IllegalArgumentException("error:bean named "+name+"is not defined");

        //实现懒加载 如果不存在bean实例再加载
        Object bean=beanDefinition.getBean();

        if (bean==null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean,name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition) throws Exception{
        //先创建bean再将bean放置到map中
        beanDefinitionMap.put(name,beanDefinition);
    }

    //在实例化后初始化bean 可以在属性注入前后做一些其他处理
    public Object initializeBean(Object bean,String beanName) throws Exception {
        for (BeanPostProcessor beanPostProcessor:
                beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,beanName);
        }

        //注入属性 完成对bean的初始化
        applyPropertyValues(bean,beanDefinitionMap.get(beanName));

        for (BeanPostProcessor beanPostProcessor:
                beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean,beanName);
        }

        return bean;
    }

    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;

    //不选择懒加载 而是提前创建并注入所有需要注入的bean
    public void preInstantiateSingletons() throws Exception {
        for (String name:
             beanDefinitionMap.keySet()) {
            getBean(name);
        }
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean=createBeanInstance(beanDefinition);
        return bean;
    }

    //根据bean的元数据创建bean实例
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return  beanDefinition.getBeanClass().newInstance();
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.add(beanPostProcessor);
    }


    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry:
             beanDefinitionMap.entrySet()) {
            if (type.isAssignableFrom(beanDefinitionEntry.getValue().getBeanClass()))
                beans.add(getBean(beanDefinitionEntry.getKey()));
        }

        return beans;
    }
}
