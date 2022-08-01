package com.tvz.skypark.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.dto.UserLoginDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.exceptions.UsernameOrEmailAlreadyInUseException;

@Service
public interface UserService {

    UserDetailsDto createNewUser(UserRegistrationDto newUser) throws UsernameOrEmailAlreadyInUseException;

    UserDetailsDto getUserByUsername(String username);

    UserDetailsDto getUserByEmail(String email);

    UserDetailsDto getUserById(Integer id);

    List<UserDetailsDto> getAllUsers();

    UserDetailsDto authenticateUser(UserLoginDto user);

    UserDetailsDto updateUser(UserDetailsDto updatedUser);
    
    void changeImage(String username, MultipartFile image);

}
