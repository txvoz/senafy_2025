package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.RoleEntity;
import edu.co.sena.senafy.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public boolean create(RoleCreateRequestDto dto){
        RoleEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<RoleResponseDto> getAll(){
        List<RoleEntity> entities = this.repository.findAll();
        List<RoleResponseDto> dtos = new ArrayList<>();
        for (RoleEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public RoleResponseDto getDetail(Long id){
        RoleEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, RoleCreateRequestDto dto) {
        RoleEntity entity = validateIfExist(id);
        RoleEntity newEntity = dtoToEntity(dto);
        entity.setName(newEntity.getName());
        entity.setDescription(newEntity.getDescription());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        RoleEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public RoleEntity validateIfExist(Long id){
        Optional<RoleEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public RoleEntity dtoToEntity(RoleCreateRequestDto dto){
        return RoleEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public RoleResponseDto entityToDto(RoleEntity entity){
        return RoleResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
