package com.car.rental.service.services;

import com.car.rental.service.dao.Car;

import java.util.List;

public interface CarService {
  Car create(Car entity);

  Car update(Car entity);

  Car findById(Long id);

  List<Car> findAll();

  String delete(Long id);

  List<Car> getAllNeedRepair();

  List<Car> getAllAvailable();

  List<Car> getAllBooked();

  List<Car> getAllByBranch(Long branchId);
}
