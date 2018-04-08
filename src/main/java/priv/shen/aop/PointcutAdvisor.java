package priv.shen.aop;

/**
 * 带有切点的通知器 继承了通知器
 * 且定义获取切点的规范
 */
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
