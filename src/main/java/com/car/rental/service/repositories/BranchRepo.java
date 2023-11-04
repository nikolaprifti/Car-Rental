package com.car.rental.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rental.service.dao.Branch;

public interface BranchRepo extends JpaRepository<Branch, Long> {

}
