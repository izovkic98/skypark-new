package com.tvz.skypark.jdbcrepository;

import java.util.Optional;
import java.util.Set;

import com.tvz.skypark.model.Rental;
import com.tvz.skypark.model.User;

public interface RentalRepository {
    Set<Rental> findAll();
    Optional<Rental> findByUser(User user);
    Optional<Rental> save(Rental rental);
    Optional<Rental> delete(Rental rental);
}
