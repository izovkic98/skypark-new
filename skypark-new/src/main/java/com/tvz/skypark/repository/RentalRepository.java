package com.tvz.skypark.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Rental;
import com.tvz.skypark.model.User;
import com.tvz.skypark.model.Vehicle;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>{

    Rental findByUserLike(User user);
    Rental findByVehicleLike(Vehicle vehicle);
    Rental findByReturnDateLike(LocalDate returnDate);
    List<Rental> findByUser_UsernameLike(String username);

}
