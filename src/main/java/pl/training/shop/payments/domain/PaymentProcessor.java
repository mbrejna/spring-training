package pl.training.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.aop.Measure;
import pl.training.shop.commons.TimeService;
import pl.training.shop.commons.aop.Retry;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;

@RequiredArgsConstructor
class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeService timeService;
    private final PaymentRepository paymentRepository;

    @Retry
    @Measure
    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getMoney())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }

}
