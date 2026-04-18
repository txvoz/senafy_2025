package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.AdViewEntity;
import edu.co.sena.senafy.repositories.AdViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdViewService {

    @Autowired
    private AdViewRepository repository;

    public boolean create(AdViewCreateRequestDto dto){
        AdViewEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<AdViewResponseDto> getAll(){
        List<AdViewEntity> entities = this.repository.findAll();
        List<AdViewResponseDto> dtos = new ArrayList<>();
        for (AdViewEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public AdViewResponseDto getDetail(Long id){
        AdViewEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, AdViewCreateRequestDto dto) {
        AdViewEntity entity = validateIfExist(id);
        AdViewEntity newEntity = dtoToEntity(dto);
        entity.setAdId(newEntity.getAdId());
        entity.setUserId(newEntity.getUserId());
        entity.setViewDate(newEntity.getViewDate());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        AdViewEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public AdViewEntity validateIfExist(Long id){
        Optional<AdViewEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public AdViewEntity dtoToEntity(AdViewCreateRequestDto dto){
        return AdViewEntity.builder()
                .adId(dto.getAdId())
                .userId(dto.getUserId())
                .viewDate(dto.getViewDate())
                .build();
    }

    public AdViewResponseDto entityToDto(AdViewEntity entity){
        return AdViewResponseDto.builder()
                .id(entity.getId())
                .adId(entity.getAdId())
                .userId(entity.getUserId())
                .viewDate(entity.getViewDate())
                .build();
    }

}
