//package org.example.metrics.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.annotation.*;
//import java.lang.reflect.Method;
//
///**
// * Created by xiedong
// * Date: 2022/10/11 20:58
// */
//@Aspect
//@Component
//@Slf4j
//@Order(0)
//public class HystrixFallbackAspect {
//    @Pointcut(value = "@annotation(org.example.metrics.aspect.HystrixFallback)")
//    public void pointcut() {
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        long startTime = System.currentTimeMillis();
//
//        HystrixFallback hystrixFallback = AnnotationUtils.getAnnotation(signature.getMethod(), HystrixFallback.class);
//        if (hystrixFallback == null) {
//            return joinPoint.proceed(joinPoint.getArgs());
//        }
//        boolean fallback = hystrixFallback.fallback();
//        if(fallback){
//            throw new RuntimeException("aaa");
//        }
//        return joinPoint.proceed(joinPoint.getArgs());
//    }
//
//}
