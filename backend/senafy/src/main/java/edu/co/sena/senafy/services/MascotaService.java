package edu.co.sena.senafy.services;

import edu.co.sena.senafy.class_.Mascota;
import edu.co.sena.senafy.dtos.MascotaCreateRequestDto;
import edu.co.sena.senafy.dtos.MascotaResponseDto;
import edu.co.sena.senafy.exceptions.ValidateIdentificationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Singleton
@Service
public class MascotaService {

    //Simular base de datos
    private List<Mascota> mascotas = new ArrayList<>();

    public boolean create(MascotaCreateRequestDto dto){
        validateIdentificacion(dto.getIdentificacion());
        Mascota entity = this.dtoToEntity(dto);
        this.mascotas.add(entity);
        return true;
    }

    public List<MascotaResponseDto> getAll(){
        List<MascotaResponseDto> dtos = new ArrayList<>();
        for (Mascota mascota : this.mascotas) {
            dtos.add(entityToDto(mascota));
        }
        return dtos;
    }

    public MascotaResponseDto getDetail(String id){
        Mascota entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(String id, MascotaCreateRequestDto dto) {
        Mascota entity = validateIfExist(id);

        if(!dto.getIdentificacion().equals(id)) {
            validateIdentificacion(dto.getIdentificacion());
        }

        Mascota newEntity = dtoToEntity(dto);
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

    public MascotaResponseDto entityToDto(Mascota entity){
        return MascotaResponseDto.builder()
                .identificacion(entity.getIdentificacion())
                .nombre(entity.getNombre())
                .raza(entity.getRaza())
                .color(entity.getColor())
                .build();
    }

    public Mascota dtoToEntity(MascotaCreateRequestDto dto) {
        return Mascota.builder()
                .identificacion(dto.getIdentificacion())
                .nombre(dto.getNombre())
                .raza(dto.getRaza())
                .color(dto.getColor())
                .build();
    }

}
