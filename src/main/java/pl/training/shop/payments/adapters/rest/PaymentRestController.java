package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.web.UriBuilder;
import pl.training.shop.payments.adapters.PaymentServiceDecorator;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentServiceDecorator paymentService;
    private final RestPaymentMapper paymentMapper;

   @PostMapping
   public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
       var paymentRequest = paymentMapper.toDomain(paymentRequestDto);
       var payment = paymentService.process(paymentRequest);
       var paymentDto = paymentMapper.toDto(payment);
       var locationUri = UriBuilder.requestUriWithId(paymentDto.id);
       return ResponseEntity.created(locationUri).body(paymentDto);
   }

}
