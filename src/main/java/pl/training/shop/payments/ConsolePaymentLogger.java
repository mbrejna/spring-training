package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger implements PaymentService {

    private final PaymentService paymentService;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = paymentService.process(paymentRequest);
        log.info(String.format("A new payment of %s has been initiated", payment.getValue()));
        return payment;
    }

}
