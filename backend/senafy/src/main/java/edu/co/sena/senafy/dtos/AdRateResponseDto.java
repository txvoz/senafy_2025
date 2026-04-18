package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdRateResponseDto {

    private Integer id;
    private Integer providerId;
    private Double costPerView;
    private java.time.LocalDate effectiveDate;

}
