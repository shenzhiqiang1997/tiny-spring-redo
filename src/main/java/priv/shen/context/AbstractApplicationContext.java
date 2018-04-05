package priv.shen.context;

import priv.shen.beans.beanDefinitionReader.AbstractBeanDefinitionReader;
import priv.shen.beans.factory.AbstractBeanFactory;

/**
 * 抽象应用上下文
 * 定义了bean工厂 去代理并增强bean工厂
 * 定义了bean定义加载器 将会用它来增强bean工厂的功能
 * 定义了初始化IOC容器的模板方法 留给子类来实现
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;
    protected AbstractBeanDefinitionReader beanDefinitionReader;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory,AbstractBeanDefinitionReader beanDefinitionReader){
        this.beanFactory=beanFactory;
        this.beanDefinitionReader=beanDefinitionReader;
    }

    public abstract void refresh() throws Exception;

    //直接调用bean工厂的方法来获取bean
    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
