package pl.training.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log
public class Repeater {

    private final int DEFAULT_ATTEMPTS_COUNT = 3;

    // @Around("@annotation(Retry)")
    @Around("@annotation(retry)")
    public Object tryExecute(ProceedingJoinPoint proceedingJoinPoint, Retry retry) throws Throwable {
        var attempts = getAttemptsCount(proceedingJoinPoint);
        var currentAttempt = 0;
        Throwable throwable;
        do {
            currentAttempt++;
            log.info(String.format("%s method execution attempt %d", proceedingJoinPoint.getSignature().getName(), currentAttempt));
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable exception) {
                throwable = exception;
            }
        } while (currentAttempt < attempts);
        throw throwable;
    }

    private int getAttemptsCount(ProceedingJoinPoint proceedingJoinPoint) {
        var targetMethod = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        return Arrays.stream(proceedingJoinPoint.getTarget().getClass().getMethods())
                .filter(method -> method.equals(targetMethod))
                .findFirst()
                .map(method -> method.getAnnotation(Retry.class))
                .map(Retry::attempts)
                .orElse(DEFAULT_ATTEMPTS_COUNT);
    }

}
