package pl.training.shop.commons.time;

import java.time.Instant;

public class SystemTimeService implements TimeService {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
