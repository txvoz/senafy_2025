package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRecordCreateRequestDto {

    private Integer userPlanId;
    private java.time.LocalDate paymentDate;
    private Double amount;
    private String paymentStatus;

}
