package priv.shen.beans.factory;

/**
 * bean工厂 定义获得bean和注册bean的规范
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
