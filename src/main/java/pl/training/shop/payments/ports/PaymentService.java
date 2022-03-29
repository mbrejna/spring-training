package pl.training.shop.payments.ports;

import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.domain.Payment;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

}
