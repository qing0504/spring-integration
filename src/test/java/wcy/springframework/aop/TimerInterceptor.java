package wcy.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerInterceptor implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long time = System.nanoTime();
        LOGGER.info("Invocation of Method " + invocation.getMethod().getName() + " start!");
        Object proceed = invocation.proceed();
        LOGGER.info("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time) + " nanoseconds.");
        return proceed;
    }

}
