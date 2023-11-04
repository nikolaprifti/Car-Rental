package com.car.rental.service.dao;

import java.time.LocalDate;

import com.car.rental.service.static_data.ReservationStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDate dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    @OneToOne(mappedBy = "refund")
    private Refund refund;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "reservations")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
