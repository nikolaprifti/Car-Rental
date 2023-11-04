package com.car.rental.service.controllers;

import com.car.rental.service.dao.Reservation;
import com.car.rental.service.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping("/create")
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam Long id) {
        return reservationService.delete(id);
    }

    @PutMapping("/complete")
    public Reservation completeReservation(@RequestParam Long id) {
        return reservationService.completeReservation(id);
    }

    @PutMapping("/cancel")
    public Reservation cancelReservation(@RequestParam Long id, @RequestParam String NID) {
        return reservationService.cancelReservation(id, NID);
    }
}