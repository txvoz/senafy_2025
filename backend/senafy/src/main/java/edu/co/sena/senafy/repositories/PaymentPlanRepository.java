package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.PaymentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPlanRepository extends
        JpaRepository<PaymentPlanEntity, Long>,
        JpaSpecificationExecutor<PaymentPlanEntity> {

}
