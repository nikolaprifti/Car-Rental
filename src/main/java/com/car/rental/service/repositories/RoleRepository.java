package com.car.rental.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rental.service.dao.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
