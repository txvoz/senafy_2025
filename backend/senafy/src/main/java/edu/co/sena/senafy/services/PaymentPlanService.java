package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.PaymentPlanEntity;
import edu.co.sena.senafy.repositories.PaymentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentPlanService {

    @Autowired
    private PaymentPlanRepository repository;

    public boolean create(PaymentPlanCreateRequestDto dto){
        PaymentPlanEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<PaymentPlanResponseDto> getAll(){
        List<PaymentPlanEntity> entities = this.repository.findAll();
        List<PaymentPlanResponseDto> dtos = new ArrayList<>();
        for (PaymentPlanEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public PaymentPlanResponseDto getDetail(Long id){
        PaymentPlanEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, PaymentPlanCreateRequestDto dto) {
        PaymentPlanEntity entity = validateIfExist(id);
        PaymentPlanEntity newEntity = dtoToEntity(dto);
        entity.setName(newEntity.getName());
        entity.setDescription(newEntity.getDescription());
        entity.setMonthlyPrice(newEntity.getMonthlyPrice());
        entity.setDurationMonths(newEntity.getDurationMonths());
        entity.setIsActive(newEntity.getIsActive());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        PaymentPlanEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public PaymentPlanEntity validateIfExist(Long id){
        Optional<PaymentPlanEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public PaymentPlanEntity dtoToEntity(PaymentPlanCreateRequestDto dto){
        return PaymentPlanEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .monthlyPrice(dto.getMonthlyPrice())
                .durationMonths(dto.getDurationMonths())
                .isActive(dto.getIsActive())
                .build();
    }

    public PaymentPlanResponseDto entityToDto(PaymentPlanEntity entity){
        return PaymentPlanResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .monthlyPrice(entity.getMonthlyPrice())
                .durationMonths(entity.getDurationMonths())
                .isActive(entity.getIsActive())
                .build();
    }

}
