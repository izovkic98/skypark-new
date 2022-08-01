package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.RentalDetailsDto;
import com.tvz.skypark.exceptions.RentalNotFoundException;
import com.tvz.skypark.service.RentalService;

@RestController
@RequestMapping("rac/rentals")
@CrossOrigin(origins = "http://localhost:3000")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<String> saveRental(@RequestBody final RentalDetailsDto newRental) {
        rentalService.createNewRental(newRental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/id/{id}")
    public RentalDetailsDto getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }
    
    @GetMapping("/user/{username}")
    public List<RentalDetailsDto> getRentalByUsername(@PathVariable String username) {
        return rentalService.getRentalByUsername(username);
    }


    @GetMapping
    public List<RentalDetailsDto> getAllRentals() {
        return rentalService.getAllRentals();
    }
    
    @PutMapping
    public ResponseEntity<String> updateRental(@RequestBody RentalDetailsDto updatedRental){
    	rentalService.updateRental(updatedRental);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRental(@PathVariable Integer id) {

        try {
            rentalService.deleteRental(id);
        } catch (RentalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
