package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad_rate")
public class AdRateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "provider_id")
    private Integer providerId;

    @Column(name = "cost_per_view")
    private Double costPerView;

    @Column(name = "effective_date")
    private java.time.LocalDate effectiveDate;

}
