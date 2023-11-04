package com.car.rental.service.dao;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    @ManyToOne
    @JoinColumn(name = "employee_refund")
    private Employee employee;

    private LocalDate dateOfReturn;

    @OneToOne
    @JoinColumn(name = "refund")
    private Reservation refund;

    private Integer surcharge;

    private String comments;
}
