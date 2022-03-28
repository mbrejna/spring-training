package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    private void log(Payment payment) {
        log.info(String.format(LOG_FORMAT, payment.getValue()));
    }

    public void init() {
        log.info("Initializing payment logger");
    }

    public void destroy() {
        log.info("Destroying payment logger");
    }

}
