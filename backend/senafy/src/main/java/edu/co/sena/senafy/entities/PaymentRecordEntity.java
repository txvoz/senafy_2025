package edu.co.sena.senafy.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_record")
public class PaymentRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_plan_id")
    private Integer userPlanId;

    @Column(name = "payment_date")
    private java.time.LocalDate paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_status")
    private String paymentStatus;

}
