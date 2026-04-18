package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.AdEntity;
import edu.co.sena.senafy.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdService {

    @Autowired
    private AdRepository repository;

    public boolean create(AdCreateRequestDto dto){
        AdEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<AdResponseDto> getAll(){
        List<AdEntity> entities = this.repository.findAll();
        List<AdResponseDto> dtos = new ArrayList<>();
        for (AdEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public AdResponseDto getDetail(Long id){
        AdEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, AdCreateRequestDto dto) {
        AdEntity entity = validateIfExist(id);
        AdEntity newEntity = dtoToEntity(dto);
        entity.setProviderId(newEntity.getProviderId());
        entity.setTitle(newEntity.getTitle());
        entity.setContent(newEntity.getContent());
        entity.setStartDate(newEntity.getStartDate());
        entity.setEndDate(newEntity.getEndDate());
        entity.setIsActive(newEntity.getIsActive());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        AdEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public AdEntity validateIfExist(Long id){
        Optional<AdEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public AdEntity dtoToEntity(AdCreateRequestDto dto){
        return AdEntity.builder()
                .providerId(dto.getProviderId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isActive(dto.getIsActive())
                .build();
    }

    public AdResponseDto entityToDto(AdEntity entity){
        return AdResponseDto.builder()
                .id(entity.getId())
                .providerId(entity.getProviderId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isActive(entity.getIsActive())
                .build();
    }

}
