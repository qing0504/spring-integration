package wcy.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wcy.springframework.core.Ordered;

import java.lang.reflect.Method;

public class AroundAdviceInterceptor implements MethodInterceptor, MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(AroundAdviceInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {
            LOGGER.info("Invocation of Method " + invocation.getMethod().getName() + " enter around advice!");
            before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            Object retVal = invocation.proceed();
            afterReturning(retVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            return retVal;
        } catch (Exception e) {
            afterThrowing(invocation.getMethod(), e);
            throw e;
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info("Invocation of Method " + method.getName() + " afterReturning advice.");
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info("Invocation of Method " + method.getName() + " before advice.");
    }

    public void afterThrowing(Method method, Throwable throwable) {
        LOGGER.info("Invocation of Method " + method.getName() + " afterThrowing advice.", throwable);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
