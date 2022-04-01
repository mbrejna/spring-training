package pl.training.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    AuthenticationManager authenticationManager;  // abstrakcja usługi do sprawdzania tożsamości
        ProviderManager providerManager; // implementuje AuthenticationManager, ma wstrzyknięte
                // AuthenticationProvider, iteruje po nich i próbuje uwierzytelnić
            AuthenticationProvider authenticationProvider; // przeprowadza uwierzytelnienie
                DaoAuthenticationProvider daoAuthenticationProvider; // przyład providera
                    UserDetailsService userDetailsService; // wykorzystywany przez DaoAuthenticationProvider do załadowania danych o user


    AccessDecisionManager accessDecisionManager; // przeprowadza autoryzację(AffirmativeBased, ConsensusBased, UnanimousBased)
        AccessDecisionVoter voter; //

    Authentication authentication; // reprezentuje stan po uwierzytelneiniu (principal, role)
    SecurityContext securityContext; // trzyma/udostępnia Authentication
    SecurityContextHolder securityContextHolder; // trzyma/udostępnia SecurityContext
    UserDetails userDetails; // reprezentuje użytkownika
    GrantedAuthority grantedAuthority; // reprezentuje role
    PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication()
                    .withUser("jan")
                        .password(passwordEncoder.encode("123"))
                        .roles("ADMIN")
                .and()
                    .withUser("marek")
                    .password(passwordEncoder.encode("123"))
                    .roles("USER");
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .groupAuthoritiesByUsername("select username, role from authorities where username = ?");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           // .csrf().disable()
        .authorizeRequests()
                .mvcMatchers("/payments/process").hasRole("ADMIN")
                .mvcMatchers("/index.html").permitAll() // .authenticated();
        .and()
            //.httpBasic()
            .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
                .permitAll()
            .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                    .logoutSuccessUrl("/index.html")
            .and()
                .exceptionHandling().accessDeniedPage("/403.html");
    }

}
