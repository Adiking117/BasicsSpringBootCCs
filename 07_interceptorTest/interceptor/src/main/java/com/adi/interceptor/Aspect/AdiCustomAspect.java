package com.adi.interceptor.Aspect;

import com.adi.interceptor.Annotation.AdiCustomAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AdiCustomAspect {

    @Around("@annotation(com.adi.interceptor.Annotation.AdiCustomAnnotation)")
    public void invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Do Something before Actual Method");

        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if(method.isAnnotationPresent(AdiCustomAnnotation.class)){
            AdiCustomAnnotation annotation = method.getAnnotation(AdiCustomAnnotation.class);
            System.out.println("Name from annotation "+ annotation.name());
        }

        joinPoint.proceed();

        System.out.println("Do Something after Actual Method");
    }
}
