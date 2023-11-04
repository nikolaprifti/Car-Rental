package com.car.rental.service.controllers;

import com.car.rental.service.dao.Refund;
import com.car.rental.service.services.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refund")
@RequiredArgsConstructor
public class RefundController {
    private final RefundService refundService;

    @GetMapping("/all")
    public List<Refund> getAll() {
        return refundService.findAll();
    }

    @GetMapping("/{id}")
    public Refund getById(@PathVariable Long id) {
        return refundService.findById(id);
    }

    @PostMapping("/create")
    public Refund create(@RequestBody Refund refund) {
        return refundService.create(refund);
    }

    @PutMapping("/update")
    public Refund update(@RequestBody Refund refund) {
        return refundService.update(refund);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam Long id) {
        return refundService.delete(id);
    }
}