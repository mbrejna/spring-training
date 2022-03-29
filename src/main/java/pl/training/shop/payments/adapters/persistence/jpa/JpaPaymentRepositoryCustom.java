package pl.training.shop.payments.adapters.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaPaymentRepositoryCustom {

    Page<PaymentEntity> getByCurrency(String currency, Pageable pageable);

}
