package wcy.springframework.aop.framework;


import java.util.Objects;

/**
 * Base class for proxy factories.
 * Provides convenient access to a configurable AopProxyFactory.
 * @author wanchongyang
 * @date 2018/10/9 11:06 AM
 */
public class ProxyCreatorSupport extends AdvisedSupport {
    private AopProxyFactory aopProxyFactory;

    /** Set to true when the first AOP proxy has been created */
    private boolean active = false;

    /**
     * Create a new ProxyCreatorSupport instance.
     */
    public ProxyCreatorSupport() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    /**
     * Create a new ProxyCreatorSupport instance.
     * @param aopProxyFactory the AopProxyFactory to use
     */
    public ProxyCreatorSupport(AopProxyFactory aopProxyFactory) {
        Objects.requireNonNull(aopProxyFactory, "AopProxyFactory must not be null");
        this.aopProxyFactory = aopProxyFactory;
    }

    /**
     * Customize the AopProxyFactory, allowing different strategies
     * to be dropped in without changing the core framework.
     * <p>Default is {@link DefaultAopProxyFactory}, using dynamic JDK
     * proxies or CGLIB proxies based on the requirements.
     */
    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        Objects.requireNonNull(aopProxyFactory, "AopProxyFactory must not be null");
        this.aopProxyFactory = aopProxyFactory;
    }

    /**
     * Return the AopProxyFactory that this ProxyConfig uses.
     */
    public AopProxyFactory getAopProxyFactory() {
        return this.aopProxyFactory;
    }

    /**
     * Subclasses should call this to get a new AOP proxy. They should <b>not</b>
     * create an AOP proxy with {@code this} as an argument.
     */
    protected final synchronized AopProxy createAopProxy() {
        if (!this.active) {
            activate();
        }
        return getAopProxyFactory().createAopProxy(this);
    }

    /**
     * Activate this proxy configuration.
     */
    private void activate() {
        this.active = true;
    }

    /**
     * Subclasses can call this to check whether any AOP proxies have been created yet.
     */
    protected final synchronized boolean isActive() {
        return this.active;
    }
}
