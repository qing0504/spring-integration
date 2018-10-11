package wcy.springframework.aop;

/**
 * 类匹配器
 */
public interface ClassFilter {

    /**
     * 用于匹配targetClass是否是要拦截的类
     *
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);

    /**
     * Canonical instance of a ClassFilter that matches all classes.
     */
    ClassFilter TRUE = new ClassFilter() {
        @Override
        public boolean matches(Class targetClass) {
            return true;
        }
    };
}
