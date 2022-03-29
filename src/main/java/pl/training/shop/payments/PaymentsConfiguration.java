package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import pl.training.shop.time.TimeService;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class PaymentsConfiguration {

    @Primary
    @Bean
    public PaymentIdGenerator uuid() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator fake() {
        return new FakePaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashSetPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(/*@Qualifier("uuid")*/ PaymentIdGenerator paymentIdGenerator, TimeService timeService, PaymentRepository paymentRepository) {
        return new PaymentProcessor(paymentIdGenerator, timeService, paymentRepository);
    }

    @Scope(SCOPE_PROTOTYPE)
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConsolePaymentLogger paymentLogger() {
        return new ConsolePaymentLogger();
    }

}
