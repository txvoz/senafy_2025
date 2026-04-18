package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdCreateRequestDto {

    private Integer providerId;
    private String title;
    private String content;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private Integer isActive;

}
