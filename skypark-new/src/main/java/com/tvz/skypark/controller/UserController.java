package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tvz.skypark.dto.JsonWebToken;
import com.tvz.skypark.dto.UserDetailsDto;
import com.tvz.skypark.dto.UserLoginDto;
import com.tvz.skypark.dto.UserRegistrationDto;
import com.tvz.skypark.exceptions.EmptyFieldsException;
import com.tvz.skypark.exceptions.UsernameOrEmailAlreadyInUseException;
import com.tvz.skypark.security.JwtFilter;
import com.tvz.skypark.security.TokenProvider;
import com.tvz.skypark.service.UserService;

@RestController
@RequestMapping("rac/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
    private UserService userService;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody final UserRegistrationDto newUser){
		try {
		userService.createNewUser(newUser);
		}
		catch (UsernameOrEmailAlreadyInUseException|EmptyFieldsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/username/{username}")
    public UserDetailsDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public UserDetailsDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/id/{id}")
    public UserDetailsDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDetailsDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JsonWebToken> authenticateUser(@RequestBody UserLoginDto user) {
    	var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

    	var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    	SecurityContextHolder.getContext().setAuthentication(authentication);

    	String jwt = tokenProvider.createToken(authentication);

    	var httpHeaders = new HttpHeaders();
    	httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    	return new ResponseEntity<>(new JsonWebToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDetailsDto updatedUser){
    	userService.updateUser(updatedUser);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @PostMapping(path="/{username}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> changeImage(@PathVariable String username, @RequestParam MultipartFile file){
    	userService.changeImage(username, file);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
