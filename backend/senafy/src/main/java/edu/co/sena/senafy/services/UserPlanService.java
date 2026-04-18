package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.UserPlanEntity;
import edu.co.sena.senafy.repositories.UserPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPlanService {

    @Autowired
    private UserPlanRepository repository;

    public boolean create(UserPlanCreateRequestDto dto){
        UserPlanEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<UserPlanResponseDto> getAll(){
        List<UserPlanEntity> entities = this.repository.findAll();
        List<UserPlanResponseDto> dtos = new ArrayList<>();
        for (UserPlanEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public UserPlanResponseDto getDetail(Long id){
        UserPlanEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, UserPlanCreateRequestDto dto) {
        UserPlanEntity entity = validateIfExist(id);
        UserPlanEntity newEntity = dtoToEntity(dto);
        entity.setUserId(newEntity.getUserId());
        entity.setPaymentPlanId(newEntity.getPaymentPlanId());
        entity.setStartDate(newEntity.getStartDate());
        entity.setEndDate(newEntity.getEndDate());
        entity.setIsActive(newEntity.getIsActive());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        UserPlanEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public UserPlanEntity validateIfExist(Long id){
        Optional<UserPlanEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public UserPlanEntity dtoToEntity(UserPlanCreateRequestDto dto){
        return UserPlanEntity.builder()
                .userId(dto.getUserId())
                .paymentPlanId(dto.getPaymentPlanId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isActive(dto.getIsActive())
                .build();
    }

    public UserPlanResponseDto entityToDto(UserPlanEntity entity){
        return UserPlanResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .paymentPlanId(entity.getPaymentPlanId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isActive(entity.getIsActive())
                .build();
    }

}
