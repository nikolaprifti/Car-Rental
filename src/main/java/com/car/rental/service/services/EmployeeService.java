package com.car.rental.service.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.car.rental.service.dao.Employee;
import com.car.rental.service.security.AuthRequest;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee update(Employee employee);

    Employee findById(Long id);

    String delete(Long id);

    ResponseEntity<?> login(AuthRequest authRequest);

    List<Employee> findAll();

    Employee getLoggedIn();
}
