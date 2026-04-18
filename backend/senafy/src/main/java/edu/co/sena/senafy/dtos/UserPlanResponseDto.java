package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPlanResponseDto {

    private Integer id;
    private Integer userId;
    private Integer paymentPlanId;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private Integer isActive;

}
