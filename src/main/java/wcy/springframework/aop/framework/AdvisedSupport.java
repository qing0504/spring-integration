package wcy.springframework.aop.framework;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import wcy.springframework.aop.Advisor;
import wcy.springframework.aop.MethodMatcher;
import wcy.springframework.aop.TargetSource;
import wcy.springframework.aop.support.DefaultAdvisorChainFactory;
import wcy.springframework.aop.support.DefaultPointcutAdvisor;
import wcy.springframework.exception.AopConfigException;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AdvisedSupport封装了TargetSource, MethodInterceptor和MethodMatcher
 */
public class AdvisedSupport implements Advised {
    /** Cache with Method as key and advisor chain List as value */
    private transient Map<MethodCacheKey, List<Object>> methodCache;

    /**
     * List of Advisors. If an Advice is added, it will be wrapped
     * in an Advisor before being added to this List.
     */
    private List<Advisor> advisors = new LinkedList<Advisor>();

    /**
     * Array updated on changes to the advisors list, which is easier
     * to manipulate internally.
     */
    private Advisor[] advisorArray = new Advisor[0];

    /** The AdvisorChainFactory to use */
    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    /**
     * 要拦截的对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     * Spring的AOP只支持方法级别的调用，所以其实在AopProxy里，我们只需要将MethodInterceptor放入对象的方法调用
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器，判断是否是需要拦截的方法
     */
    private MethodMatcher methodMatcher;

    /**
     * No-arg constructor for use as a JavaBean.
     */
    public AdvisedSupport() {
        initMethodCache();
    }

    /**
     * Initialize the method cache.
     */
    private void initMethodCache() {
        this.methodCache = new ConcurrentHashMap<>(32);
    }

    @Override
    public TargetSource getTargetSource() {
        return targetSource;
    }

    @Override
    public Advisor[] getAdvisors() {
        return this.advisorArray;
    }

    @Override
    public void addAdvisor(Advisor advisor) throws AopConfigException {
        this.advisors.add(advisor);
        updateAdvisorArray();
    }

    /**
     * Bring the array up to date with the list.
     */
    protected final void updateAdvisorArray() {
        this.advisorArray = this.advisors.toArray(new Advisor[this.advisors.size()]);
    }

    @Override
    public void addAdvice(Advice advice) throws AopConfigException {
        addAdvisor(new DefaultPointcutAdvisor(advice));
    }

    /**
     * Determine a list of {@link org.aopalliance.intercept.MethodInterceptor} objects
     * for the given method, based on this configuration.
     * @param method the proxied method
     * @param targetClass the target class
     * @return List of MethodInterceptors (may also include InterceptorAndDynamicMethodMatchers)
     */
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        MethodCacheKey cacheKey = new MethodCacheKey(method);
        List<Object> cached = this.methodCache.get(cacheKey);
        if (cached == null) {
            cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(
                    this, method, targetClass);
            this.methodCache.put(cacheKey, cached);
        }
        return cached;
    }

    @Override
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }


    /**
     * Set the advisor chain factory to use.
     * <p>Default is a {@link DefaultAdvisorChainFactory}.
     */
    public void setAdvisorChainFactory(AdvisorChainFactory advisorChainFactory) {
        Objects.requireNonNull(advisorChainFactory, "AdvisorChainFactory must not be null");
        this.advisorChainFactory = advisorChainFactory;
    }

    /**
     * Return the advisor chain factory to use (never {@code null}).
     */
    public AdvisorChainFactory getAdvisorChainFactory() {
        return this.advisorChainFactory;
    }

    /**
     * Simple wrapper class around a Method. Used as the key when
     * caching methods, for efficient equals and hashCode comparisons.
     */
    private static final class MethodCacheKey implements Comparable<MethodCacheKey> {

        private final Method method;

        private final int hashCode;

        public MethodCacheKey(Method method) {
            this.method = method;
            this.hashCode = method.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return (this == other || (other instanceof MethodCacheKey &&
                    this.method == ((MethodCacheKey) other).method));
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public String toString() {
            return this.method.toString();
        }

        @Override
        public int compareTo(MethodCacheKey other) {
            int result = this.method.getName().compareTo(other.method.getName());
            if (result == 0) {
                result = this.method.toString().compareTo(other.method.toString());
            }
            return result;
        }
    }
}
