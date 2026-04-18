package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.MascotaCreateRequestDto;
import edu.co.sena.senafy.dtos.MascotaResponseDto;
import edu.co.sena.senafy.entities.MascotaEntity;
import edu.co.sena.senafy.exceptions.ValidateIdentificationException;
import edu.co.sena.senafy.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repository;

    public boolean create(MascotaCreateRequestDto dto){
        validateIdentificacion(dto.getIdentificacion(), null);
        MascotaEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<MascotaResponseDto> getAll(){
        List<MascotaEntity> entities = this.repository.findAll();
        List<MascotaResponseDto> dtos = new ArrayList<>();
        for (MascotaEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public MascotaResponseDto getDetail(Long id){
        MascotaEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, MascotaCreateRequestDto dto) {
        MascotaEntity entity = validateIfExist(id);

        if(!entity.getIdentificacion().equals(dto.getIdentificacion())) {
            validateIdentificacion(dto.getIdentificacion(), id);
        }

        MascotaEntity newEntity = dtoToEntity(dto);
        entity.setIdentificacion(newEntity.getIdentificacion());
        entity.setNombre(newEntity.getNombre());
        entity.setRaza(newEntity.getRaza());
        entity.setColor(newEntity.getColor());

        this.repository.save(entity);

        return true;
    }

    public void delete(Long id) {
        MascotaEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public MascotaEntity validateIfExist(Long id){
        Optional<MascotaEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public void validateIdentificacion(String identificacion, Long id) {

        MascotaEntity entity = this.repository.findByIdentificacion(identificacion);
        if((Objects.isNull(id) && !Objects.isNull(entity))
                || (!Objects.isNull(id) && !Objects.isNull(entity) && !entity.getId().equals(id))
            )
        {
            throw new ValidateIdentificationException("La identificacion " + identificacion + " ya existe!");
        }
    }

    public MascotaEntity dtoToEntity(MascotaCreateRequestDto dto){
        return MascotaEntity.builder()
                .identificacion(dto.getIdentificacion())
                .nombre(dto.getNombre())
                .raza(dto.getRaza())
                .color(dto.getColor())
                .build();
    }

    public MascotaResponseDto entityToDto(MascotaEntity entity){
        return MascotaResponseDto.builder()
                .id(entity.getId())
                .identificacion(entity.getIdentificacion())
                .nombre(entity.getNombre())
                .raza(entity.getRaza())
                .color(entity.getColor())
                .build();
    }

}
