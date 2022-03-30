package pl.training.shop.payments.adapters.rest;

import lombok.Data;

@Data
public class PaymentRequestDto {

    Long requestId;
    String value;

}
