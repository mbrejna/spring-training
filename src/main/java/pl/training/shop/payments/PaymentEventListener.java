package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Log
public class PaymentEventListener {

    @Async
    @EventListener
    public void onPayment(PaymentEvent paymentEvent) {
        log.info("New payment received: " + paymentEvent.getPayment());
    }

}
