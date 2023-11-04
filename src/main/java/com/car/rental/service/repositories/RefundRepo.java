package com.car.rental.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rental.service.dao.Refund;

public interface RefundRepo extends JpaRepository<Refund, Long> {

}
