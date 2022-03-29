package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;

@Aspect
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "@annotation(pl.training.shop.payments.LogPayments)", returning = "payment")
    public void onPayment(Payment payment) {
       eventPublisher.publishEvent(new PaymentEvent(payment));
    }

}
