package priv.shen.beans.factory;

import priv.shen.aop.BeanFactoryAware;
import priv.shen.aop.TargetSource;
import priv.shen.beans.beanDefinition.BeanDefinition;
import priv.shen.beans.beanDefinition.BeanReference;
import priv.shen.beans.beanDefinition.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自动装配的bean工厂 具体实现了创建bean的方法
 */
public class AutoCapableBeanFactory extends AbstractBeanFactory {

    //将bean的属性用反射注入到bean实例中
    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition)throws Exception{
        //如果是实现了bean工厂暴露接口的类 应该将bean工厂设置为其属性
        if (bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue:
             beanDefinition.getPropertyValues().getPropertyValueList()) {
            //如果是引用的bean则要先实例化bean
            Object value=propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference=(BeanReference)value;
                value=getBean(beanReference.getName());
            }
            //正常情况下直接注入属性 如果无此属性则继续用set方法来注入
            try{
                Field beanField = bean.getClass().getDeclaredField(propertyValue.getName());
                //允许通过反射访问属性
                beanField.setAccessible(true);
                //将属性注入到bean实例中
                beanField.set(bean,value);
            }catch (NoSuchFieldException e){
                Method declaredMethod=bean.getClass().getDeclaredMethod("set"+propertyValue.getName().substring(0,1).toUpperCase()
                        +propertyValue.getName().substring(1),value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,value);
            }
        }
    }
}
