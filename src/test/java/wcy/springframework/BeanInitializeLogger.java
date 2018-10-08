package wcy.springframework;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wcy.springframework.beans.BeanPostProcessor;
import wcy.springframework.exception.BeansException;

/**
 * 实例化bean后，初始化时会调用该方法
 */
public class BeanInitializeLogger implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanInitializeLogger.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("Initialize bean " + beanName + " start!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("Initialize bean " + beanName + " end!");
        return bean;
    }
}
