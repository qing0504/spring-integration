package wcy.springframework.aop.framework;

/**
 *  Factory for AOP proxies for programmatic use, rather than via declarative
 *  setup in a bean factory. This class provides a simple way of obtaining
 *  and configuring AOP proxy instances in custom user code.
 */
public class ProxyFactory extends ProxyCreatorSupport{
    /**
     * Create a new ProxyFactory.
     */
    public ProxyFactory() {
    }

    /**
     * Create a new proxy according to the settings in this factory.
     * @return the proxy object
     */
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
}
