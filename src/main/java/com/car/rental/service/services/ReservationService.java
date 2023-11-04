package com.car.rental.service.services;

import java.util.List;

import com.car.rental.service.dao.Reservation;

public interface ReservationService {
    Reservation create(Reservation reservation);

    Reservation update(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    String delete(Long id);

    Reservation completeReservation(Long id);

    Reservation cancelReservation(Long id, String NID);
}
