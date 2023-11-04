package com.car.rental.service.services;

import java.util.List;

import com.car.rental.service.dao.Branch;

public interface BranchService {
    Branch create(Branch branch);

    Branch update(Branch branch);

    Branch findById(Long id);

    List<Branch> findAll();

    String delete(Long id);
}
