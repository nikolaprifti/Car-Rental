package com.car.rental.service.security;

import com.car.rental.service.dao.Employee;
import com.car.rental.service.repositories.EmployeeRepository;
import com.car.rental.service.exceptions.GenericExceptions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRespository;

    public UserDetailsServiceImpl(EmployeeRepository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user = employeeRespository.findByUsername(username);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw GenericExceptions.userNotFoundException("username", username);
        }
    }
}
