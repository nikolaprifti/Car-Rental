package com.car.rental.service.controllers;

import com.car.rental.service.dao.Branch;
import com.car.rental.service.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/all")
    public List<Branch> getAll() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public Branch getById(@PathVariable Long id) {
        return branchService.findById(id);
    }

    @PostMapping("/create")
    public Branch create(@RequestBody Branch branch) {
        return branchService.create(branch);
    }

    @PutMapping("/update")
    public Branch update(@RequestBody Branch branch) {
        return branchService.update(branch);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam Long id) {
        return branchService.delete(id);
    }
}