package com.stackroute.userauthenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userauthenticationservice.domain.User;
import com.stackroute.userauthenticationservice.exception.InvalidCredentialsException;
import com.stackroute.userauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.userauthenticationservice.security.SecurityTokenGenerator;
import com.stackroute.userauthenticationservice.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private  PasswordEncoder bCryptPasswordEncoder;

	private UserService userService;
	private SecurityTokenGenerator securityTokenGenerator;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
		this.userService = userService;
		this.securityTokenGenerator = securityTokenGenerator;
	}

	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
		System.out.println(user);
       Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(!auth.isAuthenticated())
        {
            throw new InvalidCredentialsException();
        }
        securityTokenGenerator.generateToken(user);
        return ResponseEntity.ok(securityTokenGenerator.generateToken(user));
    }
	
	
}
