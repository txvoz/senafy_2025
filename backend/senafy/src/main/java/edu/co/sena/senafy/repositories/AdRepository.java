package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends
        JpaRepository<AdEntity, Long>,
        JpaSpecificationExecutor<AdEntity> {

}
