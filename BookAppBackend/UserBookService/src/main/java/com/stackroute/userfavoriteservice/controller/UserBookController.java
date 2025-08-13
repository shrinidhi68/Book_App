package com.stackroute.userfavoriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userfavoriteservice.domain.Favorite;
import com.stackroute.userfavoriteservice.exception.BookNotFoundException;
import com.stackroute.userfavoriteservice.exception.UserNotFoundException;
import com.stackroute.userfavoriteservice.service.UserBookService;

@RestController
@RequestMapping("/api/v2/")
public class UserBookController {
private UserBookService userBookService;
private ResponseEntity<?> responseEntity;
@Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
//    try {
//        responseEntity =  new ResponseEntity<>(userBookService.registerUser(user), HttpStatus.CREATED);
//    }
//    catch(UserAlreadyExistsException e)
//    {
//        throw new UserAlreadyExistsException();
//    }
//    return responseEntity;
//    }
    @PostMapping("/user/favorite/{email}")
    public ResponseEntity<?> saveUserBookToList(@RequestBody Favorite favorite, @PathVariable String email) throws UserNotFoundException {
    try {
    	
        responseEntity = new ResponseEntity<>(userBookService.saveUserFavBook(favorite, email), HttpStatus.CREATED);
    }
    catch (UserNotFoundException e)
    {
        throw new UserNotFoundException();
    }
    return responseEntity;
    }
    @GetMapping("/user/favorites/{email}")
    public ResponseEntity<?> getAllUserBookFromList(@PathVariable String email) throws UserNotFoundException {
    try{
        responseEntity = new ResponseEntity<>(userBookService.getAllUserFavBooks(email), HttpStatus.OK);
    }catch(UserNotFoundException e)
    {
        throw new UserNotFoundException();
    }
       return responseEntity;
    }
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUserBookFromList(@PathVariable String email,@RequestParam String favorite)
            throws UserNotFoundException, BookNotFoundException
    {
        try {
            responseEntity = new ResponseEntity<>(userBookService.deleteUserFavBookFromList(email,favorite), HttpStatus.OK);
        } catch (UserNotFoundException | BookNotFoundException m) {
            throw new BookNotFoundException();
        }
        return responseEntity;
    }
}

