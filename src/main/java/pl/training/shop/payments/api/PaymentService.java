package pl.training.shop.payments.api;

import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.domain.Payment;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

}
