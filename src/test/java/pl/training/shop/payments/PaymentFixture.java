package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.mockito.stubbing.Answer;
import pl.training.shop.payments.adapters.persistence.jpa.PaymentEntity;
import pl.training.shop.payments.adapters.rest.PaymentDto;
import pl.training.shop.payments.domain.Payment;

import java.math.BigDecimal;
import java.time.Instant;

import static pl.training.shop.payments.domain.PaymentStatus.CONFIRMED;

public class PaymentFixture {

    public static final String TEST_ID = "00000000-0000-0000-0000-000000000001";
    public static final Instant TEST_TIME = Instant.now();
    public static final FastMoney TEST_VALUE = FastMoney.of(1_000, "PLN");
    public static final Payment TEST_PAYMENT = Payment.builder()
            .id(TEST_ID)
            .value(TEST_VALUE)
            .status(CONFIRMED)
            .timestamp(TEST_TIME)
            .build();
    public static PaymentEntity createPaymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(TEST_ID);
        entity.setValue(BigDecimal.valueOf(TEST_VALUE.getNumber().doubleValueExact()));
        entity.setCurrency(TEST_VALUE.getCurrency().getCurrencyCode());
        entity.setTimestamp(TEST_TIME);
        entity.setStatus(CONFIRMED.name());
        return entity;
    }
    public static final Answer<PaymentDto> MAP_TO_DTO = (invocation) -> {
        var payment = invocation.getArgument(0, Payment.class);
        var dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setValue(payment.getValue().toString());
        return dto;
    };

}
