package edu.co.sena.senafy.repositories;

import edu.co.sena.senafy.entities.PlaylistSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRepository extends
        JpaRepository<PlaylistSongEntity, Long>,
        JpaSpecificationExecutor<PlaylistSongEntity> {

}
