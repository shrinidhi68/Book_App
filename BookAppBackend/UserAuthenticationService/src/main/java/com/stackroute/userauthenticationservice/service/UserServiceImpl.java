package com.stackroute.userauthenticationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stackroute.userauthenticationservice.domain.User;
import com.stackroute.userauthenticationservice.exception.InvalidCredentialsException;
import com.stackroute.userauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.userauthenticationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        System.out.println(user);
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws InvalidCredentialsException {
        System.out.println("email"+email);
        System.out.println("password"+password);
        User loggedInUser = userRepository.findByEmailAndPassword(email,password);
        System.out.println(loggedInUser);
        if(loggedInUser == null)
        {
            throw new InvalidCredentialsException();
        }

        return loggedInUser;
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
	
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles("user")
				.build();
	}
}
