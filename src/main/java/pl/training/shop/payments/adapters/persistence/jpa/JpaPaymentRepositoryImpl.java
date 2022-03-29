package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaPaymentRepositoryImpl implements JpaPaymentRepositoryCustom {

    private static final String GET_BY_CURRENCY = "select p from Payment p where p.currency = :currency";

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PaymentEntity> getByCurrency(String currency, Pageable pageable) {
        var results = entityManager.createQuery(GET_BY_CURRENCY, PaymentEntity.class)
                .setParameter("currency", currency)
                .getResultList();
        return new PageImpl<>(results, pageable, -1);
    }

}
