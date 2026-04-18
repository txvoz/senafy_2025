package edu.co.sena.senafy.services;

import edu.co.sena.senafy.class_.Mascota;
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

//Singleton
@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repository;

    public boolean create(MascotaCreateRequestDto dto){
        validateIdentificacion(dto.getIdentificacion());
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

    //Simular base de datos
    private List<Mascota> mascotas = new ArrayList<>();

    public boolean create1(MascotaCreateRequestDto dto){
        validateIdentificacion1(dto.getIdentificacion());
        Mascota entity = this.dtoToEntity1(dto);
        this.mascotas.add(entity);
        return true;
    }

    public List<MascotaResponseDto> getAll1(){
        List<MascotaResponseDto> dtos = new ArrayList<>();
        for (Mascota mascota : this.mascotas) {
            dtos.add(entityToDto1(mascota));
        }
        return dtos;
    }

    public MascotaResponseDto getDetail(String id){
        Mascota entity = validateIfExist(id);
        return entityToDto1(entity);
    }

    public boolean update(String id, MascotaCreateRequestDto dto) {
        Mascota entity = validateIfExist(id);

        if(!dto.getIdentificacion().equals(id)) {
            validateIdentificacion1(dto.getIdentificacion());
        }

        Mascota newEntity = dtoToEntity1(dto);
        entity.setIdentificacion(newEntity.getIdentificacion());
        entity.setNombre(newEntity.getNombre());
        entity.setRaza(newEntity.getRaza());
        entity.setColor(newEntity.getColor());

        return true;
    }

    public void delete(String id) {
        Mascota entity = validateIfExist(id);
        this.mascotas.remove(entity);
    }

    public Mascota validateIfExist(String id){
        Mascota entity = findById(id);
        if(Objects.isNull(entity)) {
            throw new RuntimeException("El registro no existe");
        }
        return entity;
    }

    public void validateIdentificacion(String identificacion) {
        if(!Objects.isNull(this.repository.findByIdentificacion(identificacion))) {
            throw new ValidateIdentificationException("La identificacion " + identificacion + " ya existe!");
        }
    }

    public void validateIdentificacion1(String identificacion) {
        for (Mascota mascota : this.mascotas) {
            if(mascota.getIdentificacion().equals(identificacion)){
                throw new ValidateIdentificationException("La identificacion " + identificacion + " ya existe!");
            }
        }
    }

    public Mascota findById(String identificacion){
        for (Mascota mascota : this.mascotas) {
            if(mascota.getIdentificacion().equals(identificacion)){
               return mascota;
            }
        }
        return null;
    }

    public MascotaResponseDto entityToDto1(Mascota entity){
        return MascotaResponseDto.builder()
                .identificacion(entity.getIdentificacion())
                .nombre(entity.getNombre())
                .raza(entity.getRaza())
                .color(entity.getColor())
                .build();
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


    public Mascota dtoToEntity1(MascotaCreateRequestDto dto) {
        return Mascota.builder()
                .identificacion(dto.getIdentificacion())
                .nombre(dto.getNombre())
                .raza(dto.getRaza())
                .color(dto.getColor())
                .build();
    }

}
