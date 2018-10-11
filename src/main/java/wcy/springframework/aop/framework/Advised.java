package wcy.springframework.aop.framework;

import org.aopalliance.aop.Advice;
import wcy.springframework.aop.Advisor;
import wcy.springframework.aop.TargetSource;
import wcy.springframework.exception.AopConfigException;

/**
 * Interface to be implemented by classes that hold the configuration
 * of a factory of AOP proxies. This configuration includes the
 * Interceptors and other advice, Advisors.
 *
 * @author wanchongyang
 * @date 2018/10/11 10:02 AM
 */
public interface Advised {
    void setTargetSource(TargetSource targetSource);

    /**
     * Return the {@code TargetSource} used by this {@code Advised} object.
     */
    TargetSource getTargetSource();

    /**
     * Return the advisors applying to this proxy.
     * @return a list of Advisors applying to this proxy (never {@code null})
     */
    Advisor[] getAdvisors();

    /**
     * Add an advisor at the end of the advisor chain.
     * @param advisor the advisor to add to the end of the chain
     * @throws AopConfigException in case of invalid advice
     */
    void addAdvisor(Advisor advisor) throws AopConfigException;

    /**
     * Add the given AOP Alliance advice to the tail of the advice (interceptor) chain.
     * @param advice advice to add to the tail of the chain
     * @throws AopConfigException in case of invalid advice
     */
    void addAdvice(Advice advice) throws AopConfigException;
}
