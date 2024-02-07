package ru.geekbrains.springbootaophomework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HomeworkAspect {

    @Pointcut("@within(ru.geekbrains.springbootaophomework.Timer)")
    public void classPointcut() {

    }

    @Pointcut("@annotation(ru.geekbrains.springbootaophomework.Timer)")
    public void methodPointcut() {

    }

    @Around("classPointcut() || methodPointcut()")
    public Object timer(ProceedingJoinPoint joinPoint) {
        try{
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long finish = System.currentTimeMillis() - start;
            log.info("className {} - method {} # {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), finish);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
