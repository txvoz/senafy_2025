package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.PlaylistSongEntity;
import edu.co.sena.senafy.repositories.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistSongService {

    @Autowired
    private PlaylistSongRepository repository;

    public boolean create(PlaylistSongCreateRequestDto dto){
        PlaylistSongEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<PlaylistSongResponseDto> getAll(){
        List<PlaylistSongEntity> entities = this.repository.findAll();
        List<PlaylistSongResponseDto> dtos = new ArrayList<>();
        for (PlaylistSongEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public PlaylistSongResponseDto getDetail(Long id){
        PlaylistSongEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, PlaylistSongCreateRequestDto dto) {
        PlaylistSongEntity entity = validateIfExist(id);
        PlaylistSongEntity newEntity = dtoToEntity(dto);
        entity.setPlaylistId(newEntity.getPlaylistId());
        entity.setSongId(newEntity.getSongId());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        PlaylistSongEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public PlaylistSongEntity validateIfExist(Long id){
        Optional<PlaylistSongEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public PlaylistSongEntity dtoToEntity(PlaylistSongCreateRequestDto dto){
        return PlaylistSongEntity.builder()
                .playlistId(dto.getPlaylistId())
                .songId(dto.getSongId())
                .build();
    }

    public PlaylistSongResponseDto entityToDto(PlaylistSongEntity entity){
        return PlaylistSongResponseDto.builder()
                .id(entity.getId())
                .playlistId(entity.getPlaylistId())
                .songId(entity.getSongId())
                .build();
    }

}
