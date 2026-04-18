package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogCreateRequestDto {

    private Integer userId;
    private String actionType;
    private String tableName;
    private Integer recordId;
    private String description;
    private String ipAddress;
    private String userAgent;
    private java.time.LocalDateTime createdAt;

}
