package priv.shen.context;

import priv.shen.beanDefinition.BeanDefinition;
import priv.shen.beanDefinitionReader.XmlBeanDefinitionReader;
import priv.shen.factory.AutoCapableBeanFactory;
import priv.shen.resource.UrlResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文的具体实现类
 * 实现了从xml文件来初始化IOC容器的方法
 */
public class XmlApplicationContext extends AbstractApplicationContext {
    private String configurationLocation;

    public XmlApplicationContext(String configurationLocation) throws Exception {
        super(new AutoCapableBeanFactory(),new XmlBeanDefinitionReader(new UrlResourceLoader()));
        this.configurationLocation=configurationLocation;
        //一旦获取了xml配置文件地址就开始构建ICO容器
        refresh();
    }


    @Override
    public void refresh() throws Exception {
        //从xml配置文件中加载bean定义
        beanDefinitionReader.loadBeanDefinitions(configurationLocation);
        //根据bean定义调用bean工厂创建bean实例
        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry:
            beanDefinitionReader.getRegister().entrySet()) {
            //注册所有的bean定义
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),
                    beanDefinitionEntry.getValue());
        }
        //由于懒加载 所以bean的创建根据具体调用实施
    }

}
