package pl.training.shop.payments.domain;

import pl.training.shop.commons.time.TimeService;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;
import pl.training.shop.payments.api.PaymentServiceFactory;

public class DefaultPaymentServiceFactory implements PaymentServiceFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UUIDPaymentIdGenerator();

    @Override
    public PaymentService create(TimeService timeService, PaymentRepository paymentRepository) {
        return new PaymentProcessor(PAYMENT_ID_GENERATOR, timeService, paymentRepository);
    }

}
