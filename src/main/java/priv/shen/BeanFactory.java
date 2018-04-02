package priv.shen;

/**
 * 定义bean工厂的基本规范 包括bean的获取与BeanDefinition的注册
 */
public interface BeanFactory {
    //获取bean的规范
    Object getBean(String name);
    //注册beanDefinition的规范
    void registerBeanDefinition(String name,BeanDefinition  beanDefinition);
}
