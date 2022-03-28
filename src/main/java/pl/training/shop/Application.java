package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.SystemTimeService;
import pl.training.shop.payments.*;

import static pl.training.shop.commons.Money.DEFAULT_CURRENCY_UNIT;

@Log
public class Application {

    public static PaymentService paymentService() {
        var paymentIdGenerator = new UUIDPaymentIdGenerator();
        var timeService = new SystemTimeService();
        var paymentService = new PaymentProcessor(paymentIdGenerator, timeService);
        return new ConsolePaymentLoggingProxy(paymentService);
    }

    public static void main(String[] args) {
        var paymentsService = paymentService();
        var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_UNIT));
        var payment = paymentsService.process(paymentRequest);
        log.info(payment.toString());
    }

}
