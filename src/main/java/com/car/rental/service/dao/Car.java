package com.car.rental.service.dao;

import java.util.List;

import com.car.rental.service.static_data.CarStatus;
import com.car.rental.service.static_data.Fuel;
import com.car.rental.service.static_data.Transmission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Column(unique = true)
    private String model;
    private String brand;
    private Integer year;
    private String color;
    private Double mileage;
    private String description;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;
}
