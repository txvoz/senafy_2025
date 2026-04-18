package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends
        JpaRepository<SongEntity, Long>,
        JpaSpecificationExecutor<SongEntity> {

}
