package priv.shen;

/**
 * bean定义相关信息 包含bean实例
 */
public class BeanDefinition {
    //bean实例
    private Object bean;
    //bean对应的Class
    private Class beanClass;
    //bean对应Class的名称
    private String beanClassName;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        //在确定了bean对应Class的全限定名之后就可以加载该类并获得对应Class对象
        try {
            this.beanClass=Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
