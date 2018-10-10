package wcy.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 *  Common marker interface for after advice,
 *  such as {@link AfterReturningAdvice} and {@link ThrowsAdvice}.
 * @author wanchongyang
 * @date 2018/10/10 5:44 PM
 */
public interface AfterAdvice extends Advice {
}
