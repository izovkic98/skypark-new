package com.tvz.skypark.jdbcrepository;

import java.util.Optional;
import java.util.Set;

import com.tvz.skypark.model.Vehicle;

public interface VehicleRepository {
    Set<Vehicle> findAll();
    Optional<Vehicle> findByModel(String model);
    Optional<Vehicle> save(Vehicle vehicle);
    Optional<Vehicle> delete(String model, Vehicle vehicle);
}
