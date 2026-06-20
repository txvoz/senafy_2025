package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.RoleEntity;
import edu.co.sena.senafy.entities.UserEntity;
import edu.co.sena.senafy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private final static Long ID_CUSTOMER_ROLE = 1L;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleService roleService;

    public boolean logUp(UserCreateRequestDto dto){
        dto.setRoleId(ID_CUSTOMER_ROLE);
        dto.setIsPremium(0);
        dto.setRegistrationDate(LocalDateTime.now());
        UserEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public boolean create(UserCreateRequestDto dto){
        UserEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<UserResponseDto> getAll(){
        List<UserEntity> entities = this.repository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public UserResponseDto getDetail(Long id){
        UserEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, UserCreateRequestDto dto) {

        RoleEntity roleEntity = roleService.validateIfExist(dto.getRoleId());


        UserEntity entity = validateIfExist(id);
        UserEntity newEntity = dtoToEntity(dto);
        entity.setRole(roleEntity);
        entity.setIdType(newEntity.getIdType());
        entity.setIdNumber(newEntity.getIdNumber());
        entity.setFirstName(newEntity.getFirstName());
        entity.setLastName(newEntity.getLastName());
        entity.setGender(newEntity.getGender());
        entity.setEmail(newEntity.getEmail());
        entity.setPassword(newEntity.getPassword());
        entity.setRegistrationDate(newEntity.getRegistrationDate());
        entity.setIsPremium(newEntity.getIsPremium());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        UserEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public UserEntity validateIfExist(Long id){
        Optional<UserEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public UserEntity dtoToEntity(UserCreateRequestDto dto){
        RoleEntity roleEntity = roleService.validateIfExist(dto.getRoleId());
        return UserEntity.builder()
                .role(roleEntity)
                .idType(dto.getIdType())
                .idNumber(dto.getIdNumber())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .registrationDate(dto.getRegistrationDate())
                .isPremium(dto.getIsPremium())
                .build();
    }

    public UserResponseDto entityToDto(UserEntity entity){
        return UserResponseDto.builder()
                .id(entity.getId())
                .roleId(entity.getRole().getId())
                .idType(entity.getIdType())
                .idNumber(entity.getIdNumber())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .registrationDate(entity.getRegistrationDate())
                .isPremium(entity.getIsPremium())
                .build();
    }

}
