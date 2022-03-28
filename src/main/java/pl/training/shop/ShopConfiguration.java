package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.shop.commons.SystemTimeService;
import pl.training.shop.commons.TimeService;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {

    @Bean
    public TimeService timeService() {
        return new SystemTimeService();
    }

}
