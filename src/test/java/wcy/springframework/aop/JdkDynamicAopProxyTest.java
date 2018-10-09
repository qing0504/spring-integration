package wcy.springframework.aop;

import org.junit.Test;
import wcy.springframework.aop.aspectj.AspectJExpressionPointcut;
import wcy.springframework.aop.framework.AdvisedSupport;
import wcy.springframework.aop.framework.JdkDynamicAopProxy;
import wcy.springframework.service.HelloWorldService;
import wcy.springframework.service.impl.HelloWorldServiceImpl;
import wcy.springframework.context.ApplicationContext;
import wcy.springframework.context.ClassPathXmlApplicationContext;

public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(JoinPoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
                HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 设置MethodMatcher
        String expression = "execution(* wcy.springframework.service..*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        advisedSupport.setMethodMatcher(aspectJExpressionPointcut.getMethodMatcher());

        // 补：由于用户未设置MethodMatcher，所以通过代理还是调用的原方法(JdkDynamicAopProxy中的invoke方法最后
        // 返回method.invoke(...)而不是methodInterceptor.invoke(...) )
        // 4. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        // 5. 基于AOP的调用
        helloWorldServiceProxy.helloWorld();
    }
}
