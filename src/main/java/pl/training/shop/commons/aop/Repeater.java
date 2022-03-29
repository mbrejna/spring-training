package pl.training.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class Repeater {

    @Around("@annotation(retry)")
    public Object tryExecute(ProceedingJoinPoint proceedingJoinPoint, Retry retry) throws Throwable {
        var attempts = retry.attempts();
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

}
