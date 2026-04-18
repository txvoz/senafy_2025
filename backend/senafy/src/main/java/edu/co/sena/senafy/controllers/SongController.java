package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated SongCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<SongResponseDto>> getAll(){
        List<SongResponseDto> response = this.service.getAll();

        return ResponseDto.<List<SongResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<SongResponseDto> getDetail(@PathVariable Long id){
        SongResponseDto response = this.service.getDetail(id);
        return ResponseDto.<SongResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Long id,
            @RequestBody @Validated SongCreateRequestDto dto
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
