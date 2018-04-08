package priv.shen.aop;

/**
 * 被代理的对象
 * 定义了被代理的对象实例以及其实现的接口
 */
public class TargetSource {
    private Object target;
    private Class targetClass;
    private Class[] interfaces;

    public TargetSource(Object target, Class targetClass, Class[] interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Class[] getInterfaces() {
        return interfaces;
    }
}
