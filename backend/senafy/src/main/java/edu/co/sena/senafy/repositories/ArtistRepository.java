package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends
        JpaRepository<ArtistEntity, Long>,
        JpaSpecificationExecutor<ArtistEntity> {

}
