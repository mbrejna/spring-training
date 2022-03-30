package pl.training.shop.payments.domain;

import pl.training.shop.time.TimeService;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.ports.PaymentService;
import pl.training.shop.payments.ports.PaymentServiceFactory;

public class DefaultPaymentServiceFactory implements PaymentServiceFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UUIDPaymentIdGenerator();

    @Override
    public PaymentService create(TimeService timeService, PaymentRepository paymentRepository) {
        return new PaymentServiceDecorator(new PaymentProcessor(PAYMENT_ID_GENERATOR, timeService, paymentRepository));
    }

}
