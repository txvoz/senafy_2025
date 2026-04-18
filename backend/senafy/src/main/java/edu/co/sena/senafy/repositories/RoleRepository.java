package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends
        JpaRepository<RoleEntity, Long>,
        JpaSpecificationExecutor<RoleEntity> {

}
