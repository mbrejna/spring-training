package pl.training.shop.commons.aop;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionProvider {

    private final PlatformTransactionManager platformTransactionManager;
    @Setter
    private TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

    @Around("@annotation(pl.training.shop.commons.aop.Atomic)")
    public Object runWithTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        var transaction = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            var result = joinPoint.proceed();
            platformTransactionManager.commit(transaction);
            return result;
        } catch (Throwable throwable) {
            transaction.setRollbackOnly();
            throw throwable;
        }
    }

}
