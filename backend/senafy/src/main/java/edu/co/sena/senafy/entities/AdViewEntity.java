package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad_view")
public class AdViewEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ad_id")
    private Integer adId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "view_date")
    private java.time.LocalDateTime viewDate;

}
