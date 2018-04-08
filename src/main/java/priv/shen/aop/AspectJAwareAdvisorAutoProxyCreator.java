package priv.shen.aop;

import org.aopalliance.intercept.MethodInterceptor;
import priv.shen.beans.BeanPostProcessor;
import priv.shen.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * 基于AspectJ表达式的代理类自动创建器
 * 实现了bean工厂暴露接口与bean后置处理器接口
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware {
    private AbstractBeanFactory beanFactory;


    @Override
    public void setBeanFactory(AbstractBeanFactory beanFactory) {
        this.beanFactory=beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //如果是切点通知器或者方法连接器 则不需要检查是否需要产生代理类
        if (bean instanceof AspectJExpressionPointcutAdvisor)
            return bean;

        if (bean instanceof MethodInterceptor)
            return bean;

        //从IOC容器中获取到基于aspectJ表达式的切点通知器
        List<AspectJExpressionPointcutAdvisor> advisors= beanFactory
                .getBeansForType(AspectJExpressionPointcutAdvisor.class);

        //判断bean的类是否满足相应的切点匹配
        //如果是则创建代理对象
        for (AspectJExpressionPointcutAdvisor advisor:
                advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                TargetSource targetSource=new TargetSource(bean,bean.getClass(),bean.getClass().getInterfaces());
                MethodMatcher methodMatcher= advisor.getPointcut().getMethodMatcher();
                MethodInterceptor methodInterceptor= (MethodInterceptor) advisor.getAdvice();

                ProxyFactory advisedSupport=new ProxyFactory(targetSource,methodInterceptor,methodMatcher);

                return advisedSupport.getProxy();
            }
        }
        return bean;
    }
}
