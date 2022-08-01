package com.tvz.skypark.jdbcrepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Rental;
import com.tvz.skypark.model.User;

@Profile("dev")
@Repository
public class MockRentalRepository implements RentalRepository{

    private final Set<Rental> mockedRentals = new HashSet<>(
            Arrays.asList(
                    new Rental(1, "Ivan Zovkić","Mercedes AMG", "12.2.2021","1.3.2022","5.5.2022"),
                    new Rental(1, "Marko Andlar","Toyota RAV", "3.2.2022","4.3.2022","6.3.2022")
            )
    );


    @Override
    public Set<Rental> findAll() {
        return mockedRentals;
    }

    @Override
    public Optional<Rental> findByUser(User user) {
        return mockedRentals.stream().filter(rental -> rental.getUser().equals(user)).findAny();
    }

    @Override
    public Optional<Rental> save(Rental rental) {
        if(!mockedRentals.contains(rental)){
            mockedRentals.add(rental);
            return Optional.of(rental);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Rental> delete(Rental rental) {
        boolean exists = mockedRentals.removeIf(
                rental1 -> Objects.equals(rental1.getUser(), rental.getUser())
        );

        if(exists){
            mockedRentals.remove(rental);
            return Optional.of(rental);
        } else {
            return Optional.empty();
        }    }
}
