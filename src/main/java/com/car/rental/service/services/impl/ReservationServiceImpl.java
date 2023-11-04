package com.car.rental.service.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.rental.service.dao.Customer;
import com.car.rental.service.dao.Employee;
import com.car.rental.service.dao.Refund;
import com.car.rental.service.dao.Reservation;
import com.car.rental.service.repositories.CustomerRepo;
import com.car.rental.service.repositories.RefundRepo;
import com.car.rental.service.repositories.ReservationRepo;
import com.car.rental.service.exceptions.GenericExceptions;
import com.car.rental.service.services.EmployeeService;
import com.car.rental.service.services.RefundService;
import com.car.rental.service.services.ReservationService;
import com.car.rental.service.static_data.ReservationStatus;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepo;
    private final CustomerRepo customerRepo;
    private final RefundRepo refundRepo;
    private final RefundService refundService;
    private final EmployeeService employeeService;

    @Override
    public Reservation create(Reservation reservation) {
        Optional<Customer> customer = customerRepo.findByNID(reservation.getCustomer().getNID());
        if (customer.isPresent()) {
            reservation.setCustomer(customer.get());
            reservation.setAmount((reservation.getDateTo().compareTo(reservation.getDateFrom()))
                    * reservation.getCar().getPrice());
            reservation.setStatus(ReservationStatus.PENDING);
            reservationRepo.save(reservation);
            return reservation;
        } else {
            Customer newCustomer = customerRepo.save(reservation.getCustomer());
            reservation.setCustomer(newCustomer);
            reservation.setAmount((reservation.getDateTo().compareTo(reservation.getDateFrom()))
                    * reservation.getCar().getPrice());
            reservation.setStatus(ReservationStatus.PENDING);
            reservationRepo.save(reservation);
            return reservation;
        }
    }

    @Override
    public Reservation update(Reservation reservation) {
        if (reservation.getReservationId() == null) {
            throw GenericExceptions.idIsNull();
        } else {
            Reservation existingReservation = this.findById(reservation.getReservationId());
            if (reservation.getDateOfBooking() != null)
                existingReservation.setDateOfBooking(reservation.getDateOfBooking());
            if (reservation.getCar() != null)
                existingReservation.setCar(reservation.getCar());
            if (reservation.getDateFrom() != null)
                existingReservation.setDateFrom(reservation.getDateFrom());
            if (reservation.getDateTo() != null)
                existingReservation.setDateTo(reservation.getDateTo());
            if (reservation.getBranch() != null)
                existingReservation.setBranch(reservation.getBranch());

            existingReservation.setAmount((existingReservation.getDateTo().compareTo(existingReservation.getDateFrom()))
                    * existingReservation.getCar().getPrice());

            reservationRepo.save(existingReservation);
            return existingReservation;
        }
    }

    @Override
    public Reservation findById(Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.orElseThrow(() -> GenericExceptions.notFound(id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    @Override
    public String delete(Long id) {
        reservationRepo.deleteById(id);
        return String.format("Reservation with ID %d deleted", id);
    }

    @Override
    public Reservation cancelReservation(Long id, String NID) {
        Reservation reservation = this.findById(id);
        if (reservation.getCustomer().getNID() == NID) {
            reservation.setStatus(ReservationStatus.CANCELED);
            reservationRepo.save(reservation);
            return reservation;
        } else
            return null;
    }

    @Override
    public Reservation completeReservation(Long id) {
        Reservation reservation = this.findById(id);
        reservation.setStatus(ReservationStatus.COMPLETED);
        reservationRepo.save(reservation);
        Employee employee = employeeService.getLoggedIn();
        if (reservation.getDateTo().isAfter(LocalDate.now())) {
            Refund refund = new Refund();
            refund.setEmployee(employee);
            refund.setDateOfReturn(LocalDate.now());
            refund.setSurcharge(((Integer) ((int) ChronoUnit.DAYS.between(LocalDate.now(), reservation.getDateTo())))
                    * (reservation.getCar().getPrice() / 80 * 100));
            refundService.create(refund);
            refundRepo.save(refund);
        } else if (reservation.getDateTo().isBefore(LocalDate.now())) {
            Refund refund = new Refund();
            refund.setEmployee(employee);
            refund.setDateOfReturn(LocalDate.now());
            refund.setSurcharge(((Integer) ((int) ChronoUnit.DAYS.between(LocalDate.now(), reservation.getDateTo())))
                    * (reservation.getCar().getPrice() * 2));
            refundService.create(refund);
            refundRepo.save(refund);
        }
        return reservation;
    }
}