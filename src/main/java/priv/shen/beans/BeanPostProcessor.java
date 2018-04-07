package priv.shen.beans;

/**
 * bean后置处理器
 * 在bean实例化后对bean进行处理或者处理
 * 定义了在bean属性注入前或bean属性注入后对bean进行一些处理的规范
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;
    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;
}
