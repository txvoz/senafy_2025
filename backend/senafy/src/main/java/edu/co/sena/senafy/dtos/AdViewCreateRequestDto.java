package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdViewCreateRequestDto {

    private Integer adId;
    private Integer userId;
    private java.time.LocalDateTime viewDate;

}
