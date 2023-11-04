package com.car.rental.service.services.impl;

import com.car.rental.service.dao.Branch;
import com.car.rental.service.dao.Car;
import com.car.rental.service.dao.Employee;
import com.car.rental.service.exceptions.GenericExceptions;
import com.car.rental.service.repositories.CarRepository;
import com.car.rental.service.services.BranchService;
import com.car.rental.service.services.CarService;
import com.car.rental.service.services.EmployeeService;
import com.car.rental.service.static_data.CarStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final EmployeeService employeeService;
    private final BranchService branchService;

    @Override
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    @Override
    public Car update(Car car) {
        if (car.getCarId() == null) {
            throw GenericExceptions.idIsNull();
        } else {
            Car existingCar = this.findById(car.getCarId());
            if (car.getModel() != null)
                existingCar.setModel(car.getModel());
            if (car.getBrand() != null)
                existingCar.setBrand(car.getBrand());
            if (car.getYear() != null)
                existingCar.setYear(car.getYear());
            if (car.getColor() != null)
                existingCar.setColor(car.getColor());
            if (car.getMileage() != null)
                existingCar.setMileage(car.getMileage());
            if (car.getDescription() != null)
                existingCar.setDescription(car.getDescription());
            if (car.getBranch() != null)
                existingCar.setBranch(car.getBranch());
            if (car.getReservations() != null)
                existingCar.setReservations(car.getReservations());
            if (car.getTransmission() != null)
                existingCar.setTransmission(car.getTransmission());

            carRepository.save(existingCar);
            return existingCar;
        }
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> GenericExceptions.notFound(id));
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();

    }

    @Override
    public String delete(Long id) {
        carRepository.deleteById(id);
        return String.format("Car with id %d has been removed", id);
    }

    @Override
    public List<Car> getAllNeedRepair() {
        Employee employee = employeeService.getLoggedIn();
        return carRepository.findAllByBranchAndCarStatus(employee.getBranch(), CarStatus.NEED_REPAIR);
    }

    @Override
    public List<Car> getAllAvailable() {
        Employee employee = employeeService.getLoggedIn();
        return carRepository.findAllByBranchAndCarStatus(employee.getBranch(), CarStatus.AVAILABLE);
    }

    @Override
    public List<Car> getAllBooked() {
        Employee employee = employeeService.getLoggedIn();
        return carRepository.findAllByBranchAndCarStatus(employee.getBranch(), CarStatus.BOOKED);
    }

    @Override
    public List<Car> getAllByBranch(Long id) {
        Employee employee = employeeService.getLoggedIn();
        id = employee.getBranch().getBranchId();
        return carRepository.findAllByBranch(branchService.findById(id));
    }

    public List<Car> getAllByBranchFromCustomer(Long branchId) {
        Branch branch = branchService.findById(branchId);
        return carRepository.findAllByBranchAndCarStatusNot(branch,
                CarStatus.NEED_REPAIR);
    }
}
