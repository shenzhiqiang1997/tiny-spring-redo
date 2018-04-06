package priv.shen.aop;

/**
 * 类匹配接口
 * 定义了指定类是否匹配要代理的类的规范
 */
public interface ClassFilter {
    boolean matches(Class targetClass);
}
