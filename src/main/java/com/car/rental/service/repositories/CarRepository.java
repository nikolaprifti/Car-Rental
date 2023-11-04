package com.car.rental.service.repositories;

import com.car.rental.service.dao.Branch;
import com.car.rental.service.dao.Car;
import com.car.rental.service.static_data.CarStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByBranchAndCarStatus(Branch branch, CarStatus status);

    List<Car> findAllByBranch(Branch branch);

    List<Car> findAllByBranchAndCarStatusNot(Branch branch, CarStatus status);
}
