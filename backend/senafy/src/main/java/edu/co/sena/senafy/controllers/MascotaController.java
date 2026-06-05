package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    MascotaService service;

    @PostMapping()
    public ResponseDto<MascotaCreateResponseDto>
    create(
            @RequestBody @Validated
            MascotaCreateRequestDto request
    ){


        boolean response = this.service.create(request);

        /*ResponseDto<MascotaCreateResponseDto> respuesta = new ResponseDto<>();
        MascotaCreateResponseDto mascotaCreateResponseDto = new MascotaCreateResponseDto();
        mascotaCreateResponseDto.setCreated(true);
        respuesta.setData(mascotaCreateResponseDto);
        return respuesta;*/

        return ResponseDto.<MascotaCreateResponseDto>builder()
                .data(MascotaCreateResponseDto.builder()
                        .isCreated(response)
                        .build())
                .build();
    }

    @GetMapping
    public ResponseDto<List<MascotaResponseDto>> getAll(){

        List<MascotaResponseDto> response = this.service.getAll();

        return ResponseDto.<List<MascotaResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<MascotaResponseDto> getDetail(@PathVariable("id") Long id){
        MascotaResponseDto response = this.service.getDetail(id);
        return ResponseDto.<MascotaResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<MascotaCreateResponseDto> update(
            @PathVariable("id") Long id,
            @RequestBody @Validated MascotaCreateRequestDto dto) {

        boolean response = this.service.update(id, dto);

        return ResponseDto.<MascotaCreateResponseDto>builder()
                .data(MascotaCreateResponseDto.builder()
                        .isCreated(response)
                        .build())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<GeneralResponseDto> delete(@PathVariable("id") Long id){
        this.service.delete(id);
        return ResponseDto.<GeneralResponseDto>builder()
                .data(GeneralResponseDto.builder()
                        .successful(true)
                        .build())
                .build();
    }

}
