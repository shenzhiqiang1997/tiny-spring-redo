package priv.shen.aop;

import org.aopalliance.aop.Advice;

/**
 * AspectJ表达式的切点通知器
 * 实现了切点通知器接口
 * 实现了返回基于AspectJ的切点以及通知的方法
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectJExpressionPointcut aspectJExpressionPointcut
            = new AspectJExpressionPointcut();
    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression){
        aspectJExpressionPointcut.setExpression(expression);
    }

    @Override
    public Pointcut getPointcut() {
        return aspectJExpressionPointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
