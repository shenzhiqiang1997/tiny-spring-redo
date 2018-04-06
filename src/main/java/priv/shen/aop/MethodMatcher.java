package priv.shen.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配接口
 * 定义了指定方法是否匹配要代理的方法的规范
 */
public interface MethodMatcher {
    boolean matches(Method method);
}
