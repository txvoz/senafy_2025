package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends
        JpaRepository<MascotaEntity, Long>,
        JpaSpecificationExecutor<MascotaEntity> {

    MascotaEntity findByIdentificacion(String identificacion);

}
