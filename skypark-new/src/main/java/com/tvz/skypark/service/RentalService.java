package com.tvz.skypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvz.skypark.dto.RentalDetailsDto;
import com.tvz.skypark.exceptions.RentalNotFoundException;

@Service
public interface RentalService {

    RentalDetailsDto createNewRental(RentalDetailsDto newRental);

    void deleteRental(Integer id) throws RentalNotFoundException;

    List<RentalDetailsDto> getAllRentals();

    RentalDetailsDto getRentalById(Integer id);

    List<RentalDetailsDto> getRentalByUsername(String username);

    void updateRental(RentalDetailsDto updatedRental);

}
