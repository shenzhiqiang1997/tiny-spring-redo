package priv.shen;

import priv.shen.beans.BeanPostProcessor;

public class BeanPostProcessorLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessBeforeInitialization "+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessAfterInitialization "+beanName);
        return bean;
    }
}
