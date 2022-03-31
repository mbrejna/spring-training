package pl.training.shop.payments.domain;

import org.junit.jupiter.api.Test;
import pl.training.commons.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static pl.training.commons.UUID.isValidUUID;

class UUIDPaymentIdGeneratorTest {

    private final UUIDPaymentIdGenerator generator = new UUIDPaymentIdGenerator();

    @Test
    void when_get_next_then_returns_valid_uuid() {
        var id = generator.getNext();
        assertThat(id, isValidUUID);
    }

}
