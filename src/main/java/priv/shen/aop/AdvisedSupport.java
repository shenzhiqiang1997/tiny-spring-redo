package priv.shen.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理对象相关的元数据
 * 定义了被代理对象和方法拦截器
 */
public class AdvisedSupport {
    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;

    public AdvisedSupport(TargetSource targetSource,MethodInterceptor methodInterceptor){
        this.targetSource=targetSource;
        this.methodInterceptor=methodInterceptor;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }
}
