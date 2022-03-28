package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Aspect
// @Scope(SCOPE_PROTOTYPE)
@Component("paymentLogger")
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    private void log(Payment payment) {
        log.info(String.format(LOG_FORMAT, payment.getValue()));
    }

    @PostConstruct
    public void init() {
        log.info("Initializing payment logger");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying payment logger");
    }

}
