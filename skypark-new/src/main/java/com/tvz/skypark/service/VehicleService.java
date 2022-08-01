package com.tvz.skypark.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tvz.skypark.dto.VehicleDetailsDto;
import com.tvz.skypark.exceptions.VehicleNotFoundException;

@Service
public interface VehicleService{

    VehicleDetailsDto createNewVehicle(VehicleDetailsDto newVehicle);

    VehicleDetailsDto getById(Integer id);

    void delete(Integer id) throws VehicleNotFoundException;

    List<VehicleDetailsDto> getAllCars();

    List<VehicleDetailsDto> getAvailableCars();
    
    void setAvailable(int id, boolean isAvailable);
    
    void changeImage(Integer id, MultipartFile image);

}
