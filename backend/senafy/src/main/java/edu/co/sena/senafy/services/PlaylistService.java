package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.PlaylistEntity;
import edu.co.sena.senafy.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repository;

    public boolean create(PlaylistCreateRequestDto dto){
        PlaylistEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<PlaylistResponseDto> getAll(){
        List<PlaylistEntity> entities = this.repository.findAll();
        List<PlaylistResponseDto> dtos = new ArrayList<>();
        for (PlaylistEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public PlaylistResponseDto getDetail(Long id){
        PlaylistEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, PlaylistCreateRequestDto dto) {
        PlaylistEntity entity = validateIfExist(id);
        PlaylistEntity newEntity = dtoToEntity(dto);
        entity.setUserId(newEntity.getUserId());
        entity.setName(newEntity.getName());
        entity.setCreationDate(newEntity.getCreationDate());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        PlaylistEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public PlaylistEntity validateIfExist(Long id){
        Optional<PlaylistEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public PlaylistEntity dtoToEntity(PlaylistCreateRequestDto dto){
        return PlaylistEntity.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .creationDate(dto.getCreationDate())
                .build();
    }

    public PlaylistResponseDto entityToDto(PlaylistEntity entity){
        return PlaylistResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .creationDate(entity.getCreationDate())
                .build();
    }

}
