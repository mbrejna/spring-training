package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    private void log(Payment payment) {
        log.info(String.format("A new payment of %s has been initiated", payment.getValue()));
    }

}
