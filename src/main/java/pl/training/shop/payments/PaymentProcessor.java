package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import pl.training.shop.time.TimeService;
import pl.training.shop.commons.aop.Retry;

@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeService timeService;
    private final PaymentRepository paymentRepository;

    @Retry
/*    @Measure
    @LogPayments*/
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
        //return paymentRepository.save(payment);
        throw new RuntimeException();
    }

}
