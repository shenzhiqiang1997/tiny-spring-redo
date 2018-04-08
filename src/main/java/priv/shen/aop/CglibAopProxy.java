package priv.shen.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib AOP代理类
 * 实现了生成代理对象的方法
 */
public class CglibAopProxy extends AbstractAopProxy {
    public CglibAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new CglibMethodInterceptor(advisedSupport));
        return enhancer.create();

    }

    public static class CglibMethodInterceptor implements MethodInterceptor{
        private AdvisedSupport advisedSupport;
        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        public CglibMethodInterceptor(AdvisedSupport advisedSupport){
            this.advisedSupport=advisedSupport;
            this.delegateMethodInterceptor=advisedSupport.getMethodInterceptor();
        }
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if (advisedSupport.getMethodMatcher()!=null
                    &&advisedSupport.getMethodMatcher().matches(method)){
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,objects,methodProxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,objects,methodProxy).proceed();
        }
    }

    public static class CglibMethodInvocation extends ReflectiveMethodInvocation{
        private MethodProxy methodProxy;
        public CglibMethodInvocation(Object target, Method method, Object[] args,MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy= methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return methodProxy.invoke(target,args);
        }
    }
}
