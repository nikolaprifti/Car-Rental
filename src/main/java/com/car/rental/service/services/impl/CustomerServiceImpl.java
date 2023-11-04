package com.car.rental.service.services.impl;

import com.car.rental.service.dao.Customer;
import com.car.rental.service.exceptions.GenericExceptions;
import com.car.rental.service.repositories.CustomerRepo;
import com.car.rental.service.services.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    public final CustomerRepo customerRepo;

    @Override
    public Customer create(Customer customer) {
        customerRepo.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw GenericExceptions.idIsNull();
        } else {
            Customer existingCustomer = this.findById(customer.getId());
            if (existingCustomer.getNID().equals(customer.getNID())
                    || customerRepo.findByNID(customer.getNID()).isEmpty()) {
                if (customer.getName() != null)
                    existingCustomer.setName(customer.getName());
                if (customer.getEmail() != null)
                    existingCustomer.setEmail(customer.getEmail());
                if (customer.getAddress() != null)
                    existingCustomer.setAddress(customer.getAddress());
                if (customer.getCelNumber() != null)
                    existingCustomer.setCelNumber(customer.getCelNumber());

                customerRepo.save(existingCustomer);
                return existingCustomer;
            } else {
                throw GenericExceptions.notFound(customer.getId());
            }
        }
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> GenericExceptions.notFound(id));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public String delete(Long id) {
        customerRepo.deleteById(id);
        return String.format("Customer with ID %d deleted", id);
    }
}
