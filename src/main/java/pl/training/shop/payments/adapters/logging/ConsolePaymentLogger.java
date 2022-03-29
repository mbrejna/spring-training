package pl.training.shop.payments.adapters.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentRequest;

@Order(10_000)
@Aspect
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    // @Pointcut("execution(* pl.training.shop.*.PaymentProcessor.pr*(..))")
    // @Pointcut("bean(paymentProcessor)")
    @Pointcut("@annotation(pl.training.shop.payments.domain.LogPayments)")
    void process() {
    }

    @Before("process() && args(paymentRequest)")
    public void onBegin(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        // joinPoint.getSignature();
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info(String.format("A new payment of %s has been created", payment.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "exception")
    public void onFailure(RuntimeException exception) {
        log.info(String.format("Payment processing failed: %s", exception.getClass().getSimpleName()));
    }

    @After("process()")
    public void onFinish() {
        log.info("Payment processing complete");
    }

}
