package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
        JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity> {

}
