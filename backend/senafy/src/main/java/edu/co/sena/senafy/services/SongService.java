package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.SongEntity;
import edu.co.sena.senafy.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService {

    @Autowired
    private SongRepository repository;

    public boolean create(SongCreateRequestDto dto){
        SongEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<SongResponseDto> getAll(){
        List<SongEntity> entities = this.repository.findAll();
        List<SongResponseDto> dtos = new ArrayList<>();
        for (SongEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public SongResponseDto getDetail(Long id){
        SongEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, SongCreateRequestDto dto) {
        SongEntity entity = validateIfExist(id);
        SongEntity newEntity = dtoToEntity(dto);
        entity.setArtistId(newEntity.getArtistId());
        entity.setCategoryId(newEntity.getCategoryId());
        entity.setTitle(newEntity.getTitle());
        entity.setDuration(newEntity.getDuration());
        entity.setAudioUrl(newEntity.getAudioUrl());
        entity.setReleaseDate(newEntity.getReleaseDate());
        entity.setViews(newEntity.getViews());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        SongEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public SongEntity validateIfExist(Long id){
        Optional<SongEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public SongEntity dtoToEntity(SongCreateRequestDto dto){
        return SongEntity.builder()
                .artistId(dto.getArtistId())
                .categoryId(dto.getCategoryId())
                .title(dto.getTitle())
                .duration(dto.getDuration())
                .audioUrl(dto.getAudioUrl())
                .releaseDate(dto.getReleaseDate())
                .views(dto.getViews())
                .build();
    }

    public SongResponseDto entityToDto(SongEntity entity){
        return SongResponseDto.builder()
                .id(entity.getId())
                .artistId(entity.getArtistId())
                .categoryId(entity.getCategoryId())
                .title(entity.getTitle())
                .duration(entity.getDuration())
                .audioUrl(entity.getAudioUrl())
                .releaseDate(entity.getReleaseDate())
                .views(entity.getViews())
                .build();
    }

}
