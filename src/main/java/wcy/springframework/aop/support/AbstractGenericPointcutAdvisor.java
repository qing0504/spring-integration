package wcy.springframework.aop.support;

import org.aopalliance.aop.Advice;

/**
 * Abstract generic PointcutAdvisor that allows for any Advice to be configured.
 *
 * @author wanchongyang
 * @date 2018/10/11 11:05 AM
 */
public abstract class AbstractGenericPointcutAdvisor extends AbstractPointcutAdvisor {

    private Advice advice;


    /**
     * Specify the advice that this advisor should apply.
     */
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }


    @Override
    public String toString() {
        return getClass().getName() + ": advice [" + getAdvice() + "]";
    }

}
