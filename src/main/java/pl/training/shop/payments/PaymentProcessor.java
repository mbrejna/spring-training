package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.shop.time.TimeService;

@Service
@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeService timeService;
    private final PaymentRepository paymentRepository;

    /*@Autowired
    public PaymentProcessor(*//*@Qualifier("fakePaymentIdGenerator")*//* @Generator("uuid") PaymentIdGenerator paymentIdGenerator, TimeService timeService, PaymentRepository paymentRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.timeService = timeService;
        this.paymentRepository = paymentRepository;
    }*/

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .timestamp(timeService.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }

}
