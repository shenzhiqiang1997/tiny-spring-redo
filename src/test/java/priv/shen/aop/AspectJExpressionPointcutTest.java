package priv.shen.aop;

import org.junit.Assert;
import org.junit.Test;
import priv.shen.HelloService;
import priv.shen.HelloServiceImpl;

public class AspectJExpressionPointcutTest {
    @Test
    public void testMatchClass(){
        AspectJExpressionPointcut aspectJExpressionPointcut=new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* priv.shen.*.* (..))");
        boolean matches=aspectJExpressionPointcut.getClassFilter().matches(HelloService.class);
        Assert.assertTrue(matches);
    }
    @Test
    public void testMatchMethod() throws Exception{
        AspectJExpressionPointcut aspectJExpressionPointcut=new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* priv.shen.*.* (..))");
        boolean matches=aspectJExpressionPointcut.getMethodMatcher().matches(HelloService.class.getDeclaredMethod("hello"));
        Assert.assertTrue(matches);

    }
}
