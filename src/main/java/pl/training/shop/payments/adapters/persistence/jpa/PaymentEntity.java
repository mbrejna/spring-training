package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Data
public class PaymentEntity {

    public static final String GET_BY_STATUS = "paymentGetByStatus";
    public static final String COUNT_BY_STATUS = "paymentCountByStatus";

    @Id
    private String id;
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;

}
