package pl.training.shop.payments.api;

import pl.training.shop.commons.time.TimeService;

public interface PaymentServiceFactory {

    PaymentService create(TimeService timeService, PaymentRepository paymentRepository);

}
