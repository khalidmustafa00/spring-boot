package com.luv2code.aopdemo.aspect;
import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.AccessFlag;
import java.util.List;

@Aspect
@Component
@Order(2)

public class MyDemoLoggingAspect {
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint)throws Throwable{
        // print out which method we are advising on
        String method=theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>>> Executing after @Around on Method: "+method);

        // get begin timestamp
        long begin=System.currentTimeMillis();
        // now let's execute the method
        Object result=null;
        try{
            result=theProceedingJoinPoint.proceed();
        }catch (Exception exc){
            //log the exception
            System.out.println(exc.getMessage());
            //give user the custom message;
           // result="Major Accident: but no worries Your private AOP helicopter is on the way to picking up you";
            //rethrow exceptions;
            throw exc;
        }
        // get end timestamp
        long end=System.currentTimeMillis();
        // compute duration and displayed it
        long duration =end-begin;
        System.out.println("\n===== duration: "+duration/1000.0 + " seconds");
        return result;

    }
    @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint){
        //print out which method we are advising on
        String method=theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>>> Executing after @After (finally) on Method: "+method);

    }
    @AfterThrowing(
            pointcut="execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint,Throwable theExc){
        //print out which method we are advising on
        String method=theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>>> Executing after @AfterThrowing on Method: "+method);
        // log the Exception
        System.out.println("\n====>>>>> The Exception is : "+theExc);



    }
    @AfterReturning(
            pointcut="execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))",
               returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result){
        //Print out which method we are advising on
        String method=theJointPoint.getSignature().toShortString();
        System.out.println("\n====>>>>> Executing after @AfterReturning on Method: "+method);
        //Print out the result of the method call
        System.out.println("\n====>>>>> result is : "+result);
        //let's post-process the data ... let's modify it :-)
        //convert account name to uppercase
        ConvertAccountNamesToUpperCase(result);
        System.out.println("\n====>>>>> result is : "+result);
    }

    private void ConvertAccountNamesToUpperCase(List<Account> result) {
        //loop through accounts
        for(Account tempAccount:result){
            //gets upper case version of name
            String theUpperName=tempAccount.getName().toUpperCase();
            //update the name on the account
            tempAccount.setName(theUpperName);
        }

    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpression.forDaoPackageNoGetterSetter()()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>>>> Executing @Before advice on method");
        //display the method signature
        MethodSignature methodSignature=(MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: "+methodSignature);
        //display method arguments
        //get args
        Object[] args=theJoinPoint.getArgs();
        //loop thru args
        for(var tempArgs:args){
            System.out.println(tempArgs);
            if(tempArgs instanceof Account){
                //down cast and print account specific stuff
                Account theAccount=(Account) tempArgs;
                System.out.println("account name: "+theAccount.getName());
                System.out.println("account level: "+theAccount.getLevel());
            }
        }
    }

}
