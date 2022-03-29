package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;

@Aspect
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "execution(* pl.training.shop.payments.PaymentProcessor.process(pl.training.shop.payments.PaymentRequest))", returning = "payment")
    public void onPayment(Payment payment) {
       eventPublisher.publishEvent(new PaymentEvent(payment));
    }

}
