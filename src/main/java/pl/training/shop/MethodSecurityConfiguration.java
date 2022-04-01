package pl.training.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
public class MethodSecurityConfiguration extends org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration {


}
