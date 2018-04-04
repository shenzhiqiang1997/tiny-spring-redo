package priv.shen.factory;

import priv.shen.beanDefinition.BeanDefinition;
import priv.shen.beanDefinition.BeanReference;
import priv.shen.beanDefinition.PropertyValue;

import java.lang.reflect.Field;

/**
 * 自动装配的bean工厂 具体实现了创建bean的方法
 */
public class AutoCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean=createBeanInstance(beanDefinition);
        applyPropertyValues(bean,beanDefinition);
        beanDefinition.setBean(bean);
        return bean;
    }

    //根据bean的元数据创建bean实例
    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return  beanDefinition.getBeanClass().newInstance();
    }

    //将bean的属性用反射注入到bean实例中
    private void applyPropertyValues(Object bean,BeanDefinition beanDefinition)throws Exception{
        for (PropertyValue propertyValue:
             beanDefinition.getPropertyValues().getPropertyValueList()) {
            Field beanField = bean.getClass().getDeclaredField(propertyValue.getName());
            //允许通过反射访问属性
            beanField.setAccessible(true);
            Object value=propertyValue.getValue();
            //如果是引用的bean则要先实例化bean
            if (value instanceof BeanReference){
               BeanReference beanReference=(BeanReference)value;
               value=getBean(beanReference.getName());
            }
            //将属性注入到bean实例中
            beanField.set(bean,value);
        }
    }
}
