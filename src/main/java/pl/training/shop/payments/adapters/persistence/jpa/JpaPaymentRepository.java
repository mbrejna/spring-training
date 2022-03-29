package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaPaymentRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentEntity save(PaymentEntity paymentEntity) {
        entityManager.persist(paymentEntity);
        return paymentEntity;
    }

    public Optional<PaymentEntity> getById(String id) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, id));
    }

    public ResultPage<PaymentEntity> getByStatus(String status, Page page) {
        var result = entityManager.createNamedQuery(PaymentEntity.GET_BY_STATUS, PaymentEntity.class)
                .setParameter("status", status)
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getSize())
                .getResultList();
        return new ResultPage<>(result, page.getNumber(), -1);
    }

}
