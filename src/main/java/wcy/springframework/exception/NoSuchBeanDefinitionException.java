package wcy.springframework.exception;

/**
 * Exception thrown when a {@code BeanFactory} is asked for a bean instance for which it
 * cannot find a definition. This may point to a non-existing bean, a non-unique bean,
 * or a manually registered singleton instance without an associated bean definition.
 *
 * @author wanchongyang
 * @date 2018/10/8 10:42 AM
 */
public class NoSuchBeanDefinitionException extends BeansException {
    private String beanName;

    /**
     * Create a new {@code NoSuchBeanDefinitionException}.
     * @param name the name of the missing bean
     */
    public NoSuchBeanDefinitionException(String name) {
        super("No bean named '" + name + "' available");
        this.beanName = name;
    }

    public NoSuchBeanDefinitionException(String name, Throwable cause) {
        super("No bean named '" + name + "' available", cause);
        this.beanName = name;
    }

    /**
     * Create a new {@code NoSuchBeanDefinitionException}.
     * @param name the name of the missing bean
     * @param message detailed message describing the problem
     */
    public NoSuchBeanDefinitionException(String name, String message) {
        super("No bean named '" + name + "' available: " + message);
        this.beanName = name;
    }


}
