package priv.shen;

/**
 * 实现了BeanFactory的创建bean方法
 */
public class AutoCapableBeanFactory extends AbstractBeanFactory{
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean=beanDefinition.getBeanClass().newInstance();
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
