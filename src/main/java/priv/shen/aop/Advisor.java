package priv.shen.aop;

import org.aopalliance.aop.Advice;

/**
 * 通知器接口
 * 定义获取通知的规范
 */
public interface Advisor {
    Advice getAdvice();
}
