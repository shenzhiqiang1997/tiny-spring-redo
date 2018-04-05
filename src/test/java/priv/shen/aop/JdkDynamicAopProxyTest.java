package priv.shen.aop;

import org.junit.Test;
import priv.shen.HelloService;
import priv.shen.TimerInterceptor;
import priv.shen.context.ApplicationContext;
import priv.shen.context.XmlApplicationContext;

public class JdkDynamicAopProxyTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext=new XmlApplicationContext("tiny-ioc.xml");
        HelloService helloService= (HelloService) applicationContext.getBean("helloService");

        TargetSource targetSource=new TargetSource(helloService,HelloService.class);
        JdkDynamicAopProxy jdkDynamicAopProxy=new JdkDynamicAopProxy(new AdvisedSupport(targetSource,new TimerInterceptor()));

        HelloService helloServiceProxy= (HelloService) jdkDynamicAopProxy.getProxy();
        helloServiceProxy.hello();
    }
}
