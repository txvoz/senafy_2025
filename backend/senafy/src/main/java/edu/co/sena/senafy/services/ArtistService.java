package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.ArtistEntity;
import edu.co.sena.senafy.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository repository;

    public boolean create(ArtistCreateRequestDto dto){
        ArtistEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<ArtistResponseDto> getAll(){
        List<ArtistEntity> entities = this.repository.findAll();
        List<ArtistResponseDto> dtos = new ArrayList<>();
        for (ArtistEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public ArtistResponseDto getDetail(Long id){
        ArtistEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, ArtistCreateRequestDto dto) {
        ArtistEntity entity = validateIfExist(id);
        ArtistEntity newEntity = dtoToEntity(dto);
        entity.setName(newEntity.getName());
        entity.setCountry(newEntity.getCountry());
        entity.setBiography(newEntity.getBiography());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        ArtistEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public ArtistEntity validateIfExist(Long id){
        Optional<ArtistEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public ArtistEntity dtoToEntity(ArtistCreateRequestDto dto){
        return ArtistEntity.builder()
                .name(dto.getName())
                .country(dto.getCountry())
                .biography(dto.getBiography())
                .build();
    }

    public ArtistResponseDto entityToDto(ArtistEntity entity){
        return ArtistResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .country(entity.getCountry())
                .biography(entity.getBiography())
                .build();
    }

}
