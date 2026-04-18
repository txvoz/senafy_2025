package edu.co.sena.senafy.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {

    private Integer roleId;
    private String idType;
    private String idNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private java.time.LocalDateTime registrationDate;
    private Integer isPremium;

}
