package pl.training.shop.payments.adapters.rest;

import lombok.Data;
import pl.training.shop.commons.Base;
import pl.training.shop.commons.Extended;
import pl.training.shop.commons.money.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = {Base.class, Extended.class})
    Long requestId;
    @Money(groups = Extended.class)
    @Pattern(regexp = "\\d+ [A-Z]+", groups = Base.class)
    @NotNull(groups = {Base.class, Extended.class})
    String value;

}
