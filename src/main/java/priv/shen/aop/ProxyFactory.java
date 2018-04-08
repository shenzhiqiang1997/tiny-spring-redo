package priv.shen.aop;

import org.aopalliance.intercept.MethodInterceptor;

public class ProxyFactory extends AdvisedSupport implements AopProxy {
    public ProxyFactory(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        super(targetSource, methodInterceptor, methodMatcher);
    }

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final CglibAopProxy createAopProxy(){
        return new CglibAopProxy(this);
    }
}
