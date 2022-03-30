package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import pl.training.shop.time.TimeService;

@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeService timeService;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

}
