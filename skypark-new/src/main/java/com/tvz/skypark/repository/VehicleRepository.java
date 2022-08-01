package com.tvz.skypark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.enums.VehicleManufacturerEnum;
import com.tvz.skypark.enums.VehicleTypeEnum;
import com.tvz.skypark.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

    Vehicle findByManufacturerLike(VehicleManufacturerEnum vehicleManufacturer);
    Vehicle findByModelLike(String model);
    List<Vehicle> findByAvailableTrue();
    Vehicle findByTypeLike(VehicleTypeEnum vehicleType);
}
