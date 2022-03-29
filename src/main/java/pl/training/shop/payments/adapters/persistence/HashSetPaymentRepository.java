package pl.training.shop.payments.adapters.persistence;

import lombok.Setter;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.api.PaymentRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HashSetPaymentRepository implements PaymentRepository {

    @Setter
    private Set<Payment> payments = new HashSet<>();

    @Override
    public Payment save(Payment payment) {
        payments.add(payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst();
    }

}
