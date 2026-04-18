package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.AdViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adView")
public class AdViewController {

    @Autowired
    private AdViewService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated AdViewCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<AdViewResponseDto>> getAll(){
        List<AdViewResponseDto> response = this.service.getAll();

        return ResponseDto.<List<AdViewResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<AdViewResponseDto> getDetail(@PathVariable Long id){
        AdViewResponseDto response = this.service.getDetail(id);
        return ResponseDto.<AdViewResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Long id,
            @RequestBody @Validated AdViewCreateRequestDto dto
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
