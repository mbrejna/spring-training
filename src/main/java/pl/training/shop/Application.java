package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.*;
import pl.training.shop.time.SystemTimeService;

@Log
public class Application {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static PaymentService paymentService() {
        var paymentIdGenerator = new UUIDPaymentIdGenerator();
        var timeService = new SystemTimeService();
        var paymentService = new PaymentProcessor(paymentIdGenerator, timeService);
        return new ConsolePaymentLogger(paymentService);
    }

    public static void main(String[] args) {
        var paymentsService = paymentService();
        var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
        var payment = paymentsService.process(paymentRequest);
        log.info(payment.toString());
    }

}
