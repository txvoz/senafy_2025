package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderCreateRequestDto {

    private String name;
    private String email;
    private String phone;

}
