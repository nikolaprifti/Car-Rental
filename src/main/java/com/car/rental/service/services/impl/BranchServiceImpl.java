package com.car.rental.service.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.rental.service.dao.Branch;
import com.car.rental.service.exceptions.GenericExceptions;
import com.car.rental.service.repositories.BranchRepo;
import com.car.rental.service.services.BranchService;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchServiceImpl implements BranchService {
    public final BranchRepo branchRepo;

    @Override
    public Branch create(Branch branch) {
        branchRepo.save(branch);
        return branch;
    }

    @Override
    public Branch update(Branch branch) {
        if (branch.getBranchId() == null) {
            throw GenericExceptions.idIsNull();
        } else {
            Branch existingBranch = this.findById(branch.getBranchId());
            if (branch.getName() != null)
                existingBranch.setName(branch.getName());
            if (branch.getEmployees() != null)
                existingBranch.setEmployees(branch.getEmployees());
            if (branch.getCars() != null)
                existingBranch.setCars(branch.getCars());
            if (branch.getReservations() != null)
                existingBranch.setReservations(branch.getReservations());
            branchRepo.save(existingBranch);
            return existingBranch;
        }
    }

    @Override
    public Branch findById(Long id) {
        return branchRepo.findById(id).orElseThrow(() -> GenericExceptions.notFound(id));
    }

    @Override
    public List<Branch> findAll() {
        return branchRepo.findAll();
    }

    @Override
    public String delete(Long id) {
        branchRepo.deleteById(id);
        return String.format("Branch with ID %d deleted", id);
    }
}
