package priv.shen.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 基于AspectJ表达式的切点类
 * 实现了根据AspectJ表达式来匹配要代理的类和方法
 * 且在需要类匹配和方法匹配时返回自身
 * 定义了字符串表达式AspectJ表达式解析类以及AspectJ解析表达式
 */
public class AspectJExpressionPointcut implements Pointcut,ClassFilter,MethodMatcher {
    private String expression;
    private PointcutParser pointcutParser;
    private PointcutExpression pointcutExpression;
    //AspectJ表达式解析器默认支持的AspectJ语法原语
    private static Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES =
            new HashSet<>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut(){
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    //通过支持的原语来构建表达式解析器
    public AspectJExpressionPointcut(Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES){
        pointcutParser=
                PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    //检查是匹配工作的准备工作是否做完
    private void checkReadyToMatch() {
        if (pointcutExpression==null)
            pointcutExpression=buildPointcutExpression();
    }

    //根据解析器解析出AspectJ表达式对象
    private PointcutExpression buildPointcutExpression() {
        return pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method) {
        checkReadyToMatch();
        ShadowMatch shadowMatch=pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches())
            return true;
        else
            return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
