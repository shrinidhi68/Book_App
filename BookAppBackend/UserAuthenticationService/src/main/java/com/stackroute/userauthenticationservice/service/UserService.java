package com.stackroute.userauthenticationservice.service;


import com.stackroute.userauthenticationservice.domain.User;
import com.stackroute.userauthenticationservice.exception.InvalidCredentialsException;
import com.stackroute.userauthenticationservice.exception.UserAlreadyExistsException;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
    //user name and pwd is in db ot not, if not save
    User findByEmailAndPassword(String email,String password) throws InvalidCredentialsException;



}
