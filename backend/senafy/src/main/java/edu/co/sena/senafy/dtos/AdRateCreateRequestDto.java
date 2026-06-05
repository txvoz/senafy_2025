package edu.co.sena.senafy.dtos;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdRateCreateRequestDto {

    private Long providerId;
    private Double costPerView;
    private Date effectiveDate;
}
