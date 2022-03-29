package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.time.Instant;

@NamedQuery(name = PaymentEntity.GET_BY_STATUS, query = "select p from Payment p where p.status = :status")
@NamedQuery(name = PaymentEntity.COUNT_BY_STATUS, query = "select count(p.id) from Payment p where p.status = :status")
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
