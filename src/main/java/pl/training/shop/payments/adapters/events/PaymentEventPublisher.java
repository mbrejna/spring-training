package pl.training.shop.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import pl.training.shop.payments.domain.Payment;

@Aspect
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "execution(* pl.training.shop.payments.domain.PaymentProcessor.process(pl.training.shop.payments.domain.PaymentRequest))", returning = "payment")
    public void onPayment(Payment payment) {
       eventPublisher.publishEvent(new PaymentEvent(payment));
    }

}
