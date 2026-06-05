package edu.co.sena.senafy.dtos;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdRateResponseDto {

    private Integer id;
    private Integer providerId;
    private String providerName;
    private Double costPerView;
    private Date effectiveDate;
    private boolean isAdRateActive = false;

}
