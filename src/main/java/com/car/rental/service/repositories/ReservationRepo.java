package com.car.rental.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rental.service.dao.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
