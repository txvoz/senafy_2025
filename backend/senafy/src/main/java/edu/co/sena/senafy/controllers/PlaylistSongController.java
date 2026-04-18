package edu.co.sena.senafy.controllers;

import edu.co.sena.senafy.dtos.*;
import edu.co.sena.senafy.services.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlistSong")
public class PlaylistSongController {

    @Autowired
    private PlaylistSongService service;

    @PostMapping
    public ResponseDto<Boolean> create(
            @RequestBody @Validated PlaylistSongCreateRequestDto request
    ){
        boolean response = this.service.create(request);

        return ResponseDto.<Boolean>builder()
                .data(response)
                .build();
    }

    @GetMapping
    public ResponseDto<List<PlaylistSongResponseDto>> getAll(){
        List<PlaylistSongResponseDto> response = this.service.getAll();

        return ResponseDto.<List<PlaylistSongResponseDto>>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<PlaylistSongResponseDto> getDetail(@PathVariable Long id){
        PlaylistSongResponseDto response = this.service.getDetail(id);
        return ResponseDto.<PlaylistSongResponseDto>builder()
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto<Boolean> update(
            @PathVariable Long id,
            @RequestBody @Validated PlaylistSongCreateRequestDto dto
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
