package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLogginAspect {
    //some setup logger
    private Logger mylogger=Logger.getLogger(getClass().getName());
    //setup pointcut declaration;
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}
    // do the same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){ }
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        //display the method we are calling
        String theMethod=theJoinPoint.getSignature().toShortString();
        mylogger.info("=====>> in @Before calling Method: "+theMethod);
        //display the arguments to the method

        //get the arguments
        Object [] args=theJoinPoint.getArgs();
        //loop thru the arguments and display args
        for(Object tempArgs:args){
            mylogger.info("====>>> arguments: "+tempArgs);
        }
    }
    // add @AfterReturning Advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint,Object theResult){
        //display the method  we are returning from
        String theMethod=theJoinPoint.getSignature().toShortString();
        mylogger.info("=====>> in @AfterReturning: from Method: "+theMethod);
        //display data returned
        mylogger.info("=====>>> result: "+theResult);


    }
}
