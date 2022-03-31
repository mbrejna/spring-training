package pl.training.shop.payments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.time.TimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.training.shop.payments.PaymentFixture.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorTest {

    @Mock
    private PaymentRepository paymentRepository;
    private PaymentProcessor paymentProcessor;
    private Payment payment;

    @BeforeEach
    void beforeEach() {
        PaymentIdGenerator generator = () -> TEST_ID;
        TimeService timeService = () -> TEST_TIME;
        when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
        paymentProcessor = new PaymentProcessor(generator, timeService, paymentRepository);
        payment = paymentProcessor.process(new PaymentRequest(1L, TEST_VALUE));
    }

    @Test
    void given_a_payment_request_when_process_then_returns_a_payment() {
        assertEquals(TEST_PAYMENT, payment);
    }

    @Disabled("Task for integration test")
    @Test
    void given_a_payment_request_when_process_then_created_payment_is_persisted() {
        verify(paymentRepository).save(payment);
    }

}
