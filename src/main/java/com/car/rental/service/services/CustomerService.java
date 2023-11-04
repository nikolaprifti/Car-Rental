package com.car.rental.service.services;

import com.car.rental.service.dao.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    String delete(Long id);
}
