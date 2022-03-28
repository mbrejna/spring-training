package pl.training.shop.payments;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.training.shop.commons.Generator;

import java.util.UUID;

@Primary
@Generator("uuid")
// @Service("paymentIdGenerator")
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
