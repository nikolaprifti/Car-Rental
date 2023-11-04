package com.car.rental.service.controllers;

import com.car.rental.service.dao.Car;
import com.car.rental.service.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/all")
    public List<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping("/create")
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping("/update")
    public Car update(@RequestBody Car car) {
        return carService.update(car);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam Long id) {
        return carService.delete(id);
    }

    @GetMapping("/available")
    public List<Car> getAvailable() {
        return carService.getAllAvailable();
    }

    @GetMapping("/branch")
    public List<Car> getAllByBranchFromCustomer(@RequestParam Long id) {
        return carService.getAllByBranch(id);
    }
}
