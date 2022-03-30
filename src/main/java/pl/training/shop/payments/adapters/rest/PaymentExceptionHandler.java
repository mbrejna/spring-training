package pl.training.shop.payments.adapters.rest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.shop.commons.web.BaseExceptionHandler;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.payments.domain.PaymentNotFoundException;

import java.util.Locale;

@ControllerAdvice(basePackages = "pl.training.shop.payments.adapters.rest")
public class PaymentExceptionHandler extends BaseExceptionHandler {

    public PaymentExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return createResponse(exception, HttpStatus.NOT_FOUND, locale);
    }

}
