package wcy.springframework.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 封装被代理对象的方法
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object proxy;
    /**
     * 原对象
     */
    protected final Object target;

    protected final Method method;

    protected Object[] arguments;

    /**
     * List of MethodInterceptor and InterceptorAndDynamicMethodMatcher
     * that need dynamic checks.
     */
    protected final List<?> interceptorsAndDynamicMethodMatchers;

    /**
     * Index from 0 of the current interceptor we're invoking.
     * -1 until we invoke: then the current interceptor.
     */
    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
                                      List<Object> interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

    @Override
    public Object getThis() {
        return target;
    }

    /**
     * 调用被拦截对象的方法
     */
    @Override
    public Object proceed() throws Throwable {
        //  We start with an index of -1 and increment early.
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return method.invoke(target, arguments);
        }

        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        MethodInterceptor methodInterceptor = (MethodInterceptor) interceptorOrInterceptionAdvice;
        return methodInterceptor.invoke(this);

        // // 这里是调用原始对象的方法
        // // 不支持拦截器链
        // /*
        //     为了支持拦截器链，可以做出以下修改：
        //     将 proceed() 方法修改为调用代理对象的方法 method.invoke(proxy,args)。
        //     在代理对象的 InvocationHandler 的 invoke 函数中，查看拦截器列表，如果有拦截器，则调用第一个拦截器并
        //     返回，否则调用原始对象的方法。
        // */
        // return method.invoke(target, arguments);
    }

    @Override
    public Method getMethod() {
        return method;
    }

}
