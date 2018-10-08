package wcy.springframework.exception;

/**
 * Abstract superclass for all exceptions thrown in the beans package
 * and subpackages.
 *
 * @author wanchongyang
 * @date 2018/10/8 10:38 AM
 */
public abstract class BeansException extends RuntimeException {
    /**
     * Create a new BeansException with the specified message.
     * @param msg the detail message
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * Create a new BeansException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param cause the root cause
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
