package com.tvz.skypark.repository;


import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.User;

@Primary
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsernameLike(String username);
	User findByEmailLike(String email);
	User findByUsernameLikeAndPasswordLike(String username, String password);
	Optional<User> findOneByUsername(String username);
}
