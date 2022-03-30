package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.commons.web.UriBuilder;
import pl.training.shop.payments.domain.PaymentServiceDecorator;
import pl.training.shop.payments.domain.PaymentStatus;

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

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        var payment = paymentService.getById(id);
        var paymentDto = paymentMapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("confirmed")
    public ResponseEntity<ResultPageDto<PaymentDto>> getConfirmedPayments(
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "0") int pageNumber) {
        var page = new Page(pageNumber, pageSize);
        var resultPage = paymentService.getByStatus(PaymentStatus.CONFIRMED, page);
        var resultPageDto = paymentMapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

}
