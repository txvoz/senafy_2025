package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdViewResponseDto {

    private Integer id;
    private Integer adId;
    private Integer userId;
    private java.time.LocalDateTime viewDate;

}
