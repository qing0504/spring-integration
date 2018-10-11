package wcy.springframework.aop.framework;

/**
 * Singleton to publish a shared DefaultAdvisorAdapterRegistry instance.
 *
 * @author wanchongyang
 * @date 2018/10/11 3:05 PM
 */
public abstract class GlobalAdvisorAdapterRegistry {
    /**
     * Keep track of a single instance so we can return it to classes that request it.
     */
    private static AdvisorAdapterRegistry instance = new DefaultAdvisorAdapterRegistry();

    /**
     * Return the singleton {@link DefaultAdvisorAdapterRegistry} instance.
     */
    public static AdvisorAdapterRegistry getInstance() {
        return instance;
    }

    /**
     * Reset the singleton {@link DefaultAdvisorAdapterRegistry}, removing any adapters.
     */
    static void reset() {
        instance = new DefaultAdvisorAdapterRegistry();
    }
}
