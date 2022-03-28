package pl.training.shop.payments;

import pl.training.shop.commons.Generator;

@Generator("fake")
public class FakePaymentIdGenerator implements PaymentIdGenerator {

    private static final String DEFAULT_ID = "0001";

    @Override
    public String getNext() {
        return DEFAULT_ID;
    }

}
