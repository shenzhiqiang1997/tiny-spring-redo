package priv.shen.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 利用JDK动态代理实现Aop
 * 定义了代理相关元数据
 * 实现了对被代理对象的增强
 * 实现了返回代理对象的方法
 */
public class JdkDynamicAopProxy implements AopProxy,InvocationHandler {
    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport=advisedSupport;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher=advisedSupport.getMethodMatcher();
        //如果切点匹配该方法则进行切入
        if (methodMatcher!=null&&methodMatcher.matches(method)){
            MethodInvocation methodInvocation=new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args);
            MethodInterceptor methodInterceptor=advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(methodInvocation);
        }else {
            //否则调用原方法
            return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
        }
    }

    /**
     * 利用JDK动态代理生成代理类并返回代理对象
     * 且用当前代理处理类来实现方法的增强
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                advisedSupport.getTargetSource().getInterfaces(),this);
    }
}
