package pl.training.shop.payments.adapters.web;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import pl.training.shop.commons.money.FastMoneyMapper;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.domain.PaymentStatus;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface WebPaymentMapper {

    PaymentRequest toDomain(PaymentRequestViewModel vm);

    PaymentViewModel toViewModel(Payment payment);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    String toViewModel(PaymentStatus status);

}
