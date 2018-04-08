package priv.shen.aop;

/**
 * 被代理的对象
 * 定义了被代理的对象实例以及其实现的接口
 */
public class TargetSource {
    private Object target;
    private Class[] targetClass;

    public TargetSource(Object target, Class[] targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public Class[] getTargetClass() {
        return targetClass;
    }
}
