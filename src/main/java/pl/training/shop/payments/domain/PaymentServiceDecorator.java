package pl.training.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.ports.PaymentService;

import java.util.List;

@RequiredArgsConstructor
public class PaymentServiceDecorator implements PaymentService {

    private final PaymentService paymentService;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        return paymentService.process(paymentRequest);
    }

    public List<Payment> process(List<PaymentRequest> paymentRequests) {
        return paymentRequests.stream().map(this::process).toList();
    }

    @Override
    public Payment getById(String id) {
        return paymentService.getById(id);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        return paymentService.getByStatus(status, page);
    }

}
