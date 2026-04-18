package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditLog")
public class AuditLogController {

    @Autowired
    private AuditLogService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated AuditLogCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<AuditLogResponseDto>> getAll(){
        List<AuditLogResponseDto> response = this.service.getAll();

        return ResponseDto.<List<AuditLogResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<AuditLogResponseDto> getDetail(@PathVariable Long id){
        AuditLogResponseDto response = this.service.getDetail(id);
        return ResponseDto.<AuditLogResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Long id,
            @RequestBody @Validated AuditLogCreateRequestDto dto
    ){
        boolean response = this.service.update(id, dto);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<GeneralResponseDto> delete(@PathVariable Long id){
        this.service.delete(id);

        return ResponseDto.<GeneralResponseDto>builder()
                .data(GeneralResponseDto.builder()
                        .successful(true)
                        .build())
                .build();
    }

}
