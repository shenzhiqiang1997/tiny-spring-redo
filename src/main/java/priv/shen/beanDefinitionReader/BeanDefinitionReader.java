package priv.shen.beanDefinitionReader;

/**
 * bean定义加载接口
 * 定义了加载bean定义的规范
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
