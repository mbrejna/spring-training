package pl.training.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.commons.aop.Atomic;
import pl.training.shop.time.TimeService;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.ports.PaymentService;

@RequiredArgsConstructor
 class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeService timeService;
    private final PaymentRepository paymentRepository;

    @Atomic
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.CONFIRMED)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getById(String id) {
        return paymentRepository.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        return paymentRepository.getByStatus(status, page);
    }

}
