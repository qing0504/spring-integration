package wcy.springframework.aop.support;

import org.aopalliance.intercept.MethodInterceptor;
import wcy.springframework.aop.Advisor;
import wcy.springframework.aop.PointcutAdvisor;
import wcy.springframework.aop.framework.Advised;
import wcy.springframework.aop.framework.AdvisorAdapterRegistry;
import wcy.springframework.aop.framework.AdvisorChainFactory;
import wcy.springframework.aop.framework.GlobalAdvisorAdapterRegistry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple but definitive way of working out an advice chain for a Method,
 * given an {@link Advised} object. Always rebuilds each advice chain;
 * caching can be provided by subclasses.
 *
 * @author wanchongyang
 * @date 2018/10/11 2:56 PM
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Advised config, Method method, Class<?> targetClass) {
        // This is somewhat tricky... We have to process introductions first,
        // but we need to preserve order in the ultimate list.
        List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors().length);
        Class<?> actualClass = (targetClass != null ? targetClass : method.getDeclaringClass());
        AdvisorAdapterRegistry registry = GlobalAdvisorAdapterRegistry.getInstance();
        for (Advisor advisor : config.getAdvisors()) {
            if (advisor instanceof PointcutAdvisor) {
                // Add it conditionally.
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
                if (pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)) {
                    MethodInterceptor[] methodInterceptors = registry.getInterceptors(advisor);
                    interceptorList.addAll(Arrays.asList(methodInterceptors));
                } else if (pointcutAdvisor.getPointcut().getMethodMatcher().matches(method, actualClass)) {
                    interceptorList.addAll(Arrays.asList(registry.getInterceptors(advisor)));
                }
            }
        }

        return interceptorList;
    }
}
