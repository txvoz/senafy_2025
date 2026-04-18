package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.CategoryEntity;
import edu.co.sena.senafy.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public boolean create(CategoryCreateRequestDto dto){
        CategoryEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<CategoryResponseDto> getAll(){
        List<CategoryEntity> entities = this.repository.findAll();
        List<CategoryResponseDto> dtos = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public CategoryResponseDto getDetail(Long id){
        CategoryEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, CategoryCreateRequestDto dto) {
        CategoryEntity entity = validateIfExist(id);
        CategoryEntity newEntity = dtoToEntity(dto);
        entity.setName(newEntity.getName());
        entity.setDescription(newEntity.getDescription());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        CategoryEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public CategoryEntity validateIfExist(Long id){
        Optional<CategoryEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public CategoryEntity dtoToEntity(CategoryCreateRequestDto dto){
        return CategoryEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public CategoryResponseDto entityToDto(CategoryEntity entity){
        return CategoryResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
