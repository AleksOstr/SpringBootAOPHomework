package ru.geekbrains.springbootaophomework.aspect;

import com.google.common.base.Defaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.geekbrains.springbootaophomework.RecoverException;

import java.util.List;

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

    @Around("@annotation(ru.geekbrains.springbootaophomework.RecoverException)")
    public Object recoverException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            List<Class<? extends RuntimeException>> classList = List.of(signature.getMethod().getAnnotation(RecoverException.class).noRecoverFor());
            for (Class<? extends RuntimeException> clazz : classList) {
                if (clazz.isAssignableFrom(e.getClass())) {
                    throw e;
                }
            }
            return Defaults.defaultValue(signature.getMethod().getReturnType());
        }
    }

}
