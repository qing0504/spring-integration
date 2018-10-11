package wcy.springframework.aop.support;

import org.aopalliance.aop.Advice;
import wcy.springframework.aop.PointcutAdvisor;
import wcy.springframework.core.Ordered;
import wcy.springframework.core.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author wanchongyang
 * @date 2018/10/11 11:04 AM
 */
public abstract class AbstractPointcutAdvisor implements PointcutAdvisor, Ordered, Serializable {

    private Integer order;


    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        if (this.order != null) {
            return this.order;
        }
        Advice advice = getAdvice();
        if (advice instanceof Ordered) {
            return ((Ordered) advice).getOrder();
        }
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointcutAdvisor)) {
            return false;
        }
        PointcutAdvisor otherAdvisor = (PointcutAdvisor) other;
        return (ObjectUtils.nullSafeEquals(getAdvice(), otherAdvisor.getAdvice()) &&
                ObjectUtils.nullSafeEquals(getPointcut(), otherAdvisor.getPointcut()));
    }

    @Override
    public int hashCode() {
        return PointcutAdvisor.class.hashCode();
    }

}
