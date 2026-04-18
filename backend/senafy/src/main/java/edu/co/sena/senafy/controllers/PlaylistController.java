package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated PlaylistCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<PlaylistResponseDto>> getAll(){
        List<PlaylistResponseDto> response = this.service.getAll();

        return ResponseDto.<List<PlaylistResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<PlaylistResponseDto> getDetail(@PathVariable Long id){
        PlaylistResponseDto response = this.service.getDetail(id);
        return ResponseDto.<PlaylistResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Long id,
            @RequestBody @Validated PlaylistCreateRequestDto dto
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
