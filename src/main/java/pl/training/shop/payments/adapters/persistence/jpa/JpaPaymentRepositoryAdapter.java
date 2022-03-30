package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentStatus;
import pl.training.shop.payments.ports.PaymentRepository;

import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPersistencePaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = paymentMapper.toEntity(payment);
        var persistedEntity = paymentRepository.save(entity);
        return paymentMapper.toDomain(persistedEntity);
    }

    @Override
    public Optional<Payment> getById(String id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDomain);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        var pageRequest = PageRequest.of(page.getNumber(), page.getSize());
        var result = paymentRepository.getByStatus(status.name(), pageRequest);
        var data = result.getContent().stream().map(paymentMapper::toDomain).toList();
        return new ResultPage<>(data, page.getNumber(), result.getTotalPages());
    }



}
