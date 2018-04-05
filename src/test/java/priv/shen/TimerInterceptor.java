package priv.shen;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 方法计时拦截器
 */
public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long before=System.nanoTime();
        System.out.println("invoke method:"+methodInvocation.getMethod().getName()+" start.");
        Object result=methodInvocation.proceed();
        System.out.println("invoke method:"+methodInvocation.getMethod().getName()+" end.");
        long after=System.nanoTime();
        System.out.println("spend "+(after-before)+" ns");
        return result;
    }
}
