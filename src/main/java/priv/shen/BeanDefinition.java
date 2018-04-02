package priv.shen;

/**
 * bean定义信息 定义了bean的元数据以及实例
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean){
        this.bean=bean;
    }
    public Object getBean() {
        return bean;
    }
}
