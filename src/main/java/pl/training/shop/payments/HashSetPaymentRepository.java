package pl.training.shop.payments;

import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
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
