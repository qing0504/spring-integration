package wcy.springframework.exception;

/**
 * Exception thrown when a BeanFactory encounters an error when
 * attempting to create a bean from a bean definition.
 *
 * @author wanchongyang
 * @date 2018/10/8 2:20 PM
 */
public class BeanCreationException extends BeansException {
    private String beanName;

    /**
     * Create a new BeanCreationException.
     * @param msg the detail message
     */
    public BeanCreationException(String msg) {
        super(msg);
    }

    /**
     * Create a new BeanCreationException.
     * @param msg the detail message
     * @param cause the root cause
     */
    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Create a new BeanCreationException.
     * @param beanName the name of the bean requested
     * @param msg the detail message
     */
    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean" + (beanName != null ? " with name '" + beanName + "'" : "") + ": " + msg);
        this.beanName = beanName;
    }

    /**
     * Create a new BeanCreationException.
     * @param beanName the name of the bean requested
     * @param msg the detail message
     * @param cause the root cause
     */
    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    /**
     * Return the name of the bean requested, if any.
     */
    public String getBeanName() {
        return this.beanName;
    }
}
