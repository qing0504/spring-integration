package wcy.springframework.exception;

/**
 * Exception that gets thrown on illegal AOP configuration arguments.
 * @author wanchongyang
 * @date 2018/10/9 11:00 AM
 */
public class AopConfigException extends RuntimeException {
    /**
     * Constructor for AopConfigException.
     * @param msg the detail message
     */
    public AopConfigException(String msg) {
        super(msg);
    }

    /**
     * Constructor for AopConfigException.
     * @param msg the detail message
     * @param cause the root cause
     */
    public AopConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
