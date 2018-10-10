package wcy.springframework.aop;

import java.lang.reflect.Method;

/**
 * After returning advice is invoked only on normal method return, not if an
 * exception is thrown. Such advice can see the return value, but cannot change it.
 *
 * @author wanchongyang
 * @date 2018/10/10 5:57 PM
 */
public interface AfterReturningAdvice extends AfterAdvice {
    /**
     * Callback after a given method successfully returned.
     * @param returnValue the value returned by the method, if any
     * @param method method being invoked
     * @param args arguments to the method
     * @param target target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     * Any exception thrown will be returned to the caller if it's
     * allowed by the method signature. Otherwise the exception
     * will be wrapped as a runtime exception.
     */
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
