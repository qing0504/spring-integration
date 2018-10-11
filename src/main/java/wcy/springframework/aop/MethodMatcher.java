package wcy.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 */
public interface MethodMatcher {

    /**
     * 匹配该方法是否是要拦截的方法
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class targetClass);

    /**
     * Canonical instance that matches all methods.
     */
    MethodMatcher TRUE = new MethodMatcher() {
        @Override
        public boolean matches(Method method, Class targetClass) {
            return true;
        }
    };
}
