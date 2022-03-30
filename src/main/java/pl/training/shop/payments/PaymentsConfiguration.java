package pl.training.shop.payments;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import pl.training.shop.payments.domain.PaymentServiceDecorator;
import pl.training.shop.time.TimeService;
import pl.training.shop.payments.adapters.events.PaymentEventListener;
import pl.training.shop.payments.adapters.events.PaymentEventPublisher;
import pl.training.shop.payments.adapters.logging.ConsolePaymentLogger;
import pl.training.shop.payments.domain.DefaultPaymentServiceFactory;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.ports.PaymentService;
import pl.training.shop.payments.ports.PaymentServiceFactory;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class PaymentsConfiguration {

    private static final PaymentServiceFactory PAYMENT_SERVICE_FACTORY = new DefaultPaymentServiceFactory();

    @Bean
    public PaymentService paymentService(TimeService timeService, PaymentRepository paymentRepository) {
        return PAYMENT_SERVICE_FACTORY.create(timeService, paymentRepository);
    }

    @Primary
    @Bean
    public PaymentService paymentServiceDecorator(PaymentService paymentService) {
        return new PaymentServiceDecorator(paymentService);
    }

    @Scope(SCOPE_PROTOTYPE)
    @Bean
    public ConsolePaymentLogger paymentLogger() {
        return new ConsolePaymentLogger();
    }

    @Bean
    public PaymentEventPublisher paymentEventPublisher(ApplicationEventPublisher eventPublisher) {
        return new PaymentEventPublisher(eventPublisher);
    }

    @Bean
    public PaymentEventListener paymentEventListener() {
        return new PaymentEventListener();
    }

}
