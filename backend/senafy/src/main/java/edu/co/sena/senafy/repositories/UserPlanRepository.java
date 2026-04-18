package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.UserPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlanRepository extends
        JpaRepository<UserPlanEntity, Long>,
        JpaSpecificationExecutor<UserPlanEntity> {

}
