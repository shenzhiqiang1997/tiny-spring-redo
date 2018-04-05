package priv.shen.beans.beanDefinition;

/**
 * bean定义 包含bean实例和bean相关信息
 */
public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String beanClassName;
    //bean属性
    private PropertyValues propertyValues;

    public BeanDefinition(){
        propertyValues=new PropertyValues();
    }
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
        //得到bean类的全限定名之后将bean的类加载
        try {
            this.beanClass=Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
