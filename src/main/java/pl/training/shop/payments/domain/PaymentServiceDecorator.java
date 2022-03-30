package pl.training.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.ports.PaymentService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentServiceDecorator {

    private final PaymentService paymentService;

    public Payment process(PaymentRequest paymentRequest) {
        return paymentService.process(paymentRequest);
    }

    public List<Payment> process(List<PaymentRequest> paymentRequests) {
        return paymentRequests.stream().map(this::process).toList();
    }

    public Payment getById(String id) {
        return paymentService.getById(id);
    }

}