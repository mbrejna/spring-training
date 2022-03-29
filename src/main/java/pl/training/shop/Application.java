package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentService;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

@Log
public class Application {

    private static final Locale DEFAULT_LOCALE = new Locale("pl", "PL");
    private static final CurrencyUnit DEFAULT_CURRENCY_UNIT = Monetary.getCurrency(DEFAULT_LOCALE);
    private static final String CONFIGURATION_FILE = "shop.xml";

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext(CONFIGURATION_FILE)) {
            var paymentsService = context.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_UNIT));
            var payment = paymentsService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
