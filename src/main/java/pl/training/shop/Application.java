package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.ports.PaymentService;

@Log
public class Application {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {
            var paymentsService = context.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentsService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
