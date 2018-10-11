package wcy.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import wcy.springframework.aop.Advisor;
import wcy.springframework.exception.UnknownAdviceTypeException;

/**
 * @author wanchongyang
 * @date 2018/10/11 3:02 PM
 */
public interface AdvisorAdapterRegistry {
    /**
     * Return an array of AOP Alliance MethodInterceptors to allow use of the
     * given Advisor in an interception-based framework.
     * <p>Don't worry about the pointcut associated with the Advisor,
     * if it's a PointcutAdvisor: just return an interceptor.
     * @param advisor Advisor to find an interceptor for
     * @return an array of MethodInterceptors to expose this Advisor's behavior
     * @throws UnknownAdviceTypeException if the Advisor type is
     * not understood by any registered AdvisorAdapter.
     */
    MethodInterceptor[] getInterceptors(Advisor advisor) throws UnknownAdviceTypeException;
}
