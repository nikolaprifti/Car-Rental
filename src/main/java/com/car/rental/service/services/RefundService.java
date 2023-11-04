package com.car.rental.service.services;

import java.util.List;

import com.car.rental.service.dao.Refund;

public interface RefundService {
    Refund create(Refund refund);

    Refund update(Refund refund);

    Refund findById(Long id);

    List<Refund> findAll();

    String delete(Long id);
}
