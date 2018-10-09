package wcy.springframework.aop.framework;

import wcy.springframework.exception.AopConfigException;

/**
 * Interface to be implemented by factories that are able to create AOP proxies.
 * @author wanchongyang
 * @date 2018/10/9 11:02 AM
 */
public interface AopProxyFactory {
    /**
     * Create an {@link AopProxy} for the given AOP configuration.
     * @param config the AOP configuration in the form of an
     * AdvisedSupport object
     * @return the corresponding AOP proxy
     * @throws AopConfigException if the configuration is invalid
     */
    AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException;
}
