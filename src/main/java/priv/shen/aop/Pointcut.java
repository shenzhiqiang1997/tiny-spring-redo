package priv.shen.aop;

/**
 * 切点接口
 * 定义了 返回匹配类和方法 的类 的规范
 */
public interface Pointcut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
