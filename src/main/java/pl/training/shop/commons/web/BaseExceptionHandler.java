package pl.training.shop.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@RequiredArgsConstructor
public class BaseExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        exception.printStackTrace();
        return createResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, locale);
    }

    protected ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var description = getDescription(exception.getClass().getSimpleName(), locale);
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

    protected String getDescription(String key, Locale locale) {
        String description;
        try {
            description = messageSource.getMessage(key, new Object[] {}, locale);
        } catch (NoSuchMessageException exception) {
            description = key;
        }
        return description;
    }

}
