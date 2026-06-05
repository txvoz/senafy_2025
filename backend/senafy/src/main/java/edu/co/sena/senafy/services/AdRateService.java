package edu.co.sena.senafy.services;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.entities.AdRateEntity;
import edu.co.sena.senafy.entities.ProviderEntity;
import edu.co.sena.senafy.repositories.AdRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdRateService {

    @Autowired
    private AdRateRepository repository;

    @Autowired
    private ProviderService providerService;

    public boolean create(AdRateCreateRequestDto dto){
        AdRateEntity entity = this.dtoToEntity(dto);
        this.repository.save(entity);
        return true;
    }

    public List<AdRateResponseDto> getAll(){
        List<AdRateEntity> entities = this.repository.findAll();
        List<AdRateResponseDto> dtos = new ArrayList<>();
        for (AdRateEntity entity : entities) {
            dtos.add(this.entityToDto(entity));
        }
        return dtos;
    }

    public AdRateResponseDto getDetail(Long id){
        AdRateEntity entity = validateIfExist(id);
        return entityToDto(entity);
    }

    public boolean update(Long id, AdRateCreateRequestDto dto) {
        AdRateEntity entity = validateIfExist(id);
        AdRateEntity newEntity = dtoToEntity(dto);
        //entity.setProviderId(newEntity.getProviderId());
        entity.setCostPerView(newEntity.getCostPerView());
        entity.setEffectiveDate(newEntity.getEffectiveDate());

        this.repository.save(entity);
        return true;
    }

    public void delete(Long id) {
        AdRateEntity entity = validateIfExist(id);
        this.repository.delete(entity);
    }

    public AdRateEntity validateIfExist(Long id){
        Optional<AdRateEntity> optEntity = this.repository.findById(id);
        if(optEntity.isEmpty()) {
            throw new RuntimeException("El registro no existe");
        }
        return optEntity.get();
    }

    public AdRateEntity dtoToEntity(AdRateCreateRequestDto dto){

        ProviderEntity providerEntity = this.providerService.validateIfExist(dto.getProviderId());

        return AdRateEntity.builder()
                .provider(providerEntity)
                .costPerView(dto.getCostPerView())
                .effectiveDate(dto.getEffectiveDate())
                .build();
    }

    public AdRateResponseDto entityToDto(AdRateEntity entity){
        Date now = new Date();

        return AdRateResponseDto.builder()
                .id(entity.getId())
                .providerId(entity.getProvider().getId())
                .providerName(entity.getProvider().getName())
                .costPerView(entity.getCostPerView())
                .effectiveDate(entity.getEffectiveDate())
                .isAdRateActive(entity.getEffectiveDate().getTime() >= now.getTime())
                .build();
    }

}
