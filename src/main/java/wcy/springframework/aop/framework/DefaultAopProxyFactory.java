package wcy.springframework.aop.framework;

import wcy.springframework.exception.AopConfigException;

import java.lang.reflect.Proxy;

/**
 * Default {@link AopProxyFactory} implementation, creating either a CGLIB proxy
 * or a JDK dynamic proxy.
 * @author wanchongyang
 * @date 2018/10/9 11:03 AM
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
        Class<?> targetClass = config.getTargetSource().getTargetClass();
        if (targetClass == null) {
            throw new AopConfigException("TargetSource cannot determine target class: " +
                    "Either an interface or a target is required for proxy creation.");
        }

        if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
            return new JdkDynamicAopProxy(config);
        }

        return new Cglib2AopProxy(config);
    }
}
