package priv.shen.beans.beanDefinitionReader;

import priv.shen.beans.beanDefinition.BeanDefinition;
import priv.shen.beans.resource.ResourceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象bean定义加载类
 * 加载bean定义的方法作为模板方法留给实现类来实现
 * 定义了加载的bean定义的容器map
 * 以及加载资源的加载器
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private ResourceLoader resourceLoader;
    private Map<String,BeanDefinition> register;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
        register = new ConcurrentHashMap<>();
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public Map<String, BeanDefinition> getRegister() {
        return register;
    }
}
