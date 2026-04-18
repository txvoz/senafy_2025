package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPlanResponseDto {

    private Integer id;
    private String name;
    private String description;
    private Double monthlyPrice;
    private Integer durationMonths;
    private Integer isActive;

}
