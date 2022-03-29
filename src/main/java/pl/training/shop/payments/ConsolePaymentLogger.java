package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    public void log(Payment payment) {
        log.info(String.format("A new payment of %s has been initiated", payment.getValue()));
    }

    public void init() {
        log.info("Initializing payment logger");
    }

    public void destroy() {
        log.info("Destroying payment logger");
    }

}
