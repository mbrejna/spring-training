package pl.training.shop.payments.ports;

import pl.training.shop.commons.time.TimeService;

public interface PaymentServiceFactory {

    PaymentService create(TimeService timeService, PaymentRepository paymentRepository);

}
