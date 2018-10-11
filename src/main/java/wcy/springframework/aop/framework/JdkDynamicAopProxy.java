package wcy.springframework.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import wcy.springframework.aop.support.AbstractAopProxy;
import wcy.springframework.aop.ReflectiveMethodInvocation;

/**
 * 一个基于JDK的动态代理
 * 只能针对实现了接口的类生成代理。于是我们就有了基本的织入功能。
 * 注意：实现了InvocationHandler接口,可以通过重写invoke方法进行控制访问
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    /**
     * 获取代理对象
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                advised.getTargetSource().getInterfaces(), this);
    }

    /**
     * 控制访问
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object target = getTarget();
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, target.getClass());
        if (chain == null || chain.isEmpty()) {
            return method.invoke(target, args);
        }

        MethodInvocation invocation = new ReflectiveMethodInvocation(proxy, target, method, args, chain);
        return invocation.proceed();

        // // 从AdvisedSupport里获取方法拦截器
        // MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
        // // 如果方法匹配器存在，且匹配该对象的该方法匹配成功,则调用用户提供的方法拦截器的invoke方法
        // if (advised.getMethodMatcher() != null
        //         && advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
        //     return methodInterceptor.invoke(new ReflectiveMethodInvocation(proxy, target, method, args, chain));
        // } else {
        //     // 否则的话还是调用原对象的相关方法
        //     return method.invoke(advised.getTargetSource().getTarget(), args);
        // }
    }

    private Object getTarget() {
        return this.advised.getTargetSource().getTarget();
    }
}
