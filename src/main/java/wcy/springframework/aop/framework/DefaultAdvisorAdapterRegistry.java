package wcy.springframework.aop.framework;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import wcy.springframework.aop.Advisor;
import wcy.springframework.exception.UnknownAdviceTypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the {@link AdvisorAdapterRegistry} interface.
 *
 * @author wanchongyang
 * @date 2018/10/11 3:04 PM
 */
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry {
    @Override
    public MethodInterceptor[] getInterceptors(Advisor advisor) throws UnknownAdviceTypeException {
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>(3);
        Advice advice = advisor.getAdvice();
        if (advice instanceof MethodInterceptor) {
            interceptors.add((MethodInterceptor) advice);
        }

        if (interceptors.isEmpty()) {
            throw new UnknownAdviceTypeException(advisor.getAdvice());
        }

        return interceptors.toArray(new MethodInterceptor[interceptors.size()]);
    }
}
