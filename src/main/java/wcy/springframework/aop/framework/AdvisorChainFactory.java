package wcy.springframework.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Factory interface for advisor chains.
 *
 * @author wanchongyang
 * @date 2018/10/11 2:55 PM
 */
public interface AdvisorChainFactory {

    /**
     * Determine a list of {@link org.aopalliance.intercept.MethodInterceptor} objects
     * for the given advisor chain configuration.
     * @param config the AOP configuration in the form of an Advised object
     * @param method the proxied method
     * @param targetClass the target class (may be {@code null} to indicate a proxy without
     * target object, in which case the method's declaring class is the next best option)
     * @return List of MethodInterceptors (may also include InterceptorAndDynamicMethodMatchers)
     */
    List<Object> getInterceptorsAndDynamicInterceptionAdvice(Advised config, Method method, Class<?> targetClass);
}
