package pl.training.shop.payments.api;

import pl.training.shop.payments.domain.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> getById(String id);

}
