package pl.training.shop.payments.adapters.persistence.jpa;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentStatus;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface JpaPersistencePaymentMapper {

    default PaymentEntity toEntity(Payment payment) {
        var paymentValue = payment.getValue();
        var entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setValue(BigDecimal.valueOf(paymentValue.getNumber().doubleValueExact()));
        entity.setCurrency(paymentValue.getCurrency().getCurrencyCode());
        entity.setStatus(payment.getStatus().name());
        entity.setTimestamp(payment.getTimestamp());
        return entity;
    }

    default Payment toDomain(PaymentEntity entity) {
        return Payment.builder()
                .id(entity.getId())
                .value(FastMoney.of(entity.getValue(), entity.getCurrency()))
                .status(PaymentStatus.valueOf(entity.getStatus()))
                .timestamp(entity.getTimestamp())
                .build();
    }

}
