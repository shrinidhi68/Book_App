package com.stackroute.userfavoriteservice.service;

import com.stackroute.userfavoriteservice.domain.Favorite;

import com.stackroute.userfavoriteservice.domain.User;
import com.stackroute.userfavoriteservice.exception.BookNotFoundException;
import com.stackroute.userfavoriteservice.exception.UserAlreadyExistsException;
import com.stackroute.userfavoriteservice.exception.UserNotFoundException;

import java.util.List;

public interface UserBookService {
User registerUser(User user) throws UserAlreadyExistsException;
User saveUserFavBookt(Favorite favorite,String email) throws UserNotFoundException;
User deleteUserFavBookFromList(String email,String favUrl) throws UserNotFoundException, BookNotFoundException;
List<Favorite> getAllUserFavBooks(String email) throws UserNotFoundException;
}
