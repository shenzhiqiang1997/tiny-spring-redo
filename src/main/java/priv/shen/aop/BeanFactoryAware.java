package priv.shen.aop;

import priv.shen.beans.factory.AbstractBeanFactory;

/**
 * 暴露beanFactory的接口
 */
public interface BeanFactoryAware {
    void setBeanFactory(AbstractBeanFactory beanFactory);
}
