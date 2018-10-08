package wcy.springframework.aop;

import org.junit.Test;
import wcy.springframework.service.HelloWorldService;
import wcy.springframework.service.impl.HelloWorldServiceImpl;
import wcy.springframework.context.ApplicationContext;
import wcy.springframework.context.ClassPathXmlApplicationContext;

public class Cglib2AopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // // --------- helloWorldService with AOP
        // // 1. 设置被代理对象(Joinpoint)
        // AdvisedSupport advisedSupport = new AdvisedSupport();
        // TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        // advisedSupport.setTargetSource(targetSource);
        //
        // // 2. 设置拦截器(Advice)
        // TimerInterceptor timerInterceptor = new TimerInterceptor();
        // advisedSupport.setMethodInterceptor(timerInterceptor);
        //
        // // 补：没有设置MethodMatcher，所以拦截该类的所有方法
        // // 3. 创建代理(Proxy)
        // Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        // HelloWorldService helloWorldServiceProxy = (HelloWorldService) cglib2AopProxy.getProxy();
        //
        // // 4. 基于AOP的调用
        // helloWorldServiceProxy.helloWorld();
    }
}
