package pl.training.shop.payments.adapters.persistence;

import lombok.Setter;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentStatus;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

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

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        var data = payments.stream()
                .filter(payment -> payment.getStatus().equals(status))
                .collect(toList());
        var totalPages = (int) Math.ceil((double) data.size() / page.size());
        return new ResultPage<>(data, page.number(), totalPages);
    }

}
