package pl.training.shop.payments.adapters.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentViewModel implements Serializable {

    String value;
    String status;

}
