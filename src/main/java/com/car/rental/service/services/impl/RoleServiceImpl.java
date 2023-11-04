package com.car.rental.service.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.car.rental.service.dao.Role;
import com.car.rental.service.repositories.RoleRepository;
import com.car.rental.service.services.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
