package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.PaymentRecordEntity;
import edu.co.sena.senafy.repositories.PaymentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentRecordService {

    @Autowired
    private PaymentRecordRepository repository;

    public boolean create(PaymentRecordCreateRequestDto dto){
        PaymentRecordEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<PaymentRecordResponseDto> getAll(){
        List<PaymentRecordEntity> entities = this.repository.findAll();
        List<PaymentRecordResponseDto> dtos = new ArrayList<>();
        for (PaymentRecordEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public PaymentRecordResponseDto getDetail(Long Id){
        PaymentRecordEntity entity = valIdateIfExist(Id);
        return entityToDto(entity);
    }

    public boolean update(Long Id, PaymentRecordCreateRequestDto dto) {
        PaymentRecordEntity entity = valIdateIfExist(Id);
        PaymentRecordEntity newEntity = dtoToEntity(dto);
        entity.setUserPlanId(newEntity.getUserPlanId());
        entity.setPaymentDate(newEntity.getPaymentDate());
        entity.setAmount(newEntity.getAmount());
        entity.setPaymentStatus(newEntity.getPaymentStatus());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long Id) {
        PaymentRecordEntity entity = valIdateIfExist(Id);
        this.repository.delete(entity);
    }

    public PaymentRecordEntity valIdateIfExist(Long Id){
        Optional<PaymentRecordEntity> optEntity = this.repository.findById(Id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public PaymentRecordEntity dtoToEntity(PaymentRecordCreateRequestDto dto){
        return PaymentRecordEntity.builder()
                .userPlanId(dto.getUserPlanId())
                .paymentDate(dto.getPaymentDate())
                .amount(dto.getAmount())
                .paymentStatus(dto.getPaymentStatus())
                .build();
    }

    public PaymentRecordResponseDto entityToDto(PaymentRecordEntity entity){
        return PaymentRecordResponseDto.builder()
                .id(entity.getId())
                .userPlanId(entity.getUserPlanId())
                .paymentDate(entity.getPaymentDate())
                .amount(entity.getAmount())
                .paymentStatus(entity.getPaymentStatus())
                .build();
    }

}
