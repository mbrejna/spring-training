package pl.training.shop.time;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SystemTimeService implements TimeService {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
