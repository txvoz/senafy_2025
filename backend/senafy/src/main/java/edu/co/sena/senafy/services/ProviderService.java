package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.ProviderEntity;
import edu.co.sena.senafy.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;

    public boolean create(ProviderCreateRequestDto dto){
        ProviderEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ProviderResponseDto> getAll(){
        List<ProviderEntity> entities = this.repository.findAll();
        List<ProviderResponseDto> dtos = new ArrayList<>();
        for (ProviderEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ProviderResponseDto getDetail(Long id){
        ProviderEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, ProviderCreateRequestDto dto) {
        ProviderEntity entity = validateIfExist(id);
        ProviderEntity newEntity = dtoToEntity(dto);
        entity.setName(newEntity.getName());
        entity.setEmail(newEntity.getEmail());
        entity.setPhone(newEntity.getPhone());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        ProviderEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ProviderEntity validateIfExist(Long id){
        Optional<ProviderEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ProviderEntity dtoToEntity(ProviderCreateRequestDto dto){
        return ProviderEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public ProviderResponseDto entityToDto(ProviderEntity entity){
        return ProviderResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }

}
