package com.stackroute.userauthenticationservice.controller;

import com.netflix.discovery.converters.Auto;
import com.stackroute.userauthenticationservice.domain.User;
import com.stackroute.userauthenticationservice.exception.InvalidCredentialsException;
import com.stackroute.userauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.userauthenticationservice.security.SecurityTokenGenerator;
import com.stackroute.userauthenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {

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
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
       Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(!auth.isAuthenticated())
        {
            throw new InvalidCredentialsException();
        }
        return ResponseEntity.ok("Succes");
    }

}
