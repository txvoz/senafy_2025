package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends
        JpaRepository<ProviderEntity, Long>,
        JpaSpecificationExecutor<ProviderEntity> {

}
