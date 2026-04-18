package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.PaymentRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRecordRepository extends
        JpaRepository<PaymentRecordEntity, Long>,
        JpaSpecificationExecutor<PaymentRecordEntity> {

}
