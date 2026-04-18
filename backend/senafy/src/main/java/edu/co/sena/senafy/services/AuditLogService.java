package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.AuditLogEntity;
import edu.co.sena.senafy.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository repository;

    public boolean create(AuditLogCreateRequestDto dto){
        AuditLogEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<AuditLogResponseDto> getAll(){
        List<AuditLogEntity> entities = this.repository.findAll();
        List<AuditLogResponseDto> dtos = new ArrayList<>();
        for (AuditLogEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public AuditLogResponseDto getDetail(Long id){
        AuditLogEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, AuditLogCreateRequestDto dto) {
        AuditLogEntity entity = validateIfExist(id);
        AuditLogEntity newEntity = dtoToEntity(dto);
        entity.setUserId(newEntity.getUserId());
        entity.setActionType(newEntity.getActionType());
        entity.setTableName(newEntity.getTableName());
        entity.setRecordId(newEntity.getRecordId());
        entity.setDescription(newEntity.getDescription());
        entity.setIpAddress(newEntity.getIpAddress());
        entity.setUserAgent(newEntity.getUserAgent());
        entity.setCreatedAt(newEntity.getCreatedAt());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        AuditLogEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public AuditLogEntity validateIfExist(Long id){
        Optional<AuditLogEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public AuditLogEntity dtoToEntity(AuditLogCreateRequestDto dto){
        return AuditLogEntity.builder()
                .userId(dto.getUserId())
                .actionType(dto.getActionType())
                .tableName(dto.getTableName())
                .recordId(dto.getRecordId())
                .description(dto.getDescription())
                .ipAddress(dto.getIpAddress())
                .userAgent(dto.getUserAgent())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public AuditLogResponseDto entityToDto(AuditLogEntity entity){
        return AuditLogResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .actionType(entity.getActionType())
                .tableName(entity.getTableName())
                .recordId(entity.getRecordId())
                .description(entity.getDescription())
                .ipAddress(entity.getIpAddress())
                .userAgent(entity.getUserAgent())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
