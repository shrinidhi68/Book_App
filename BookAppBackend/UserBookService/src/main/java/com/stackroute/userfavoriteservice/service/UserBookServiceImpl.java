package com.stackroute.userfavoriteservice.service;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userfavoriteservice.domain.Favorite;
import com.stackroute.userfavoriteservice.domain.User;
import com.stackroute.userfavoriteservice.exception.BookNotFoundException;
import com.stackroute.userfavoriteservice.exception.UserAlreadyExistsException;
import com.stackroute.userfavoriteservice.exception.UserNotFoundException;
import com.stackroute.userfavoriteservice.repository.UserBookRepository;
@Service
public class UserBookServiceImpl implements UserBookService{
    private UserBookRepository userBookRepository;
    @Autowired
    public UserBookServiceImpl(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userBookRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        return userBookRepository.save(user);
    }

    @Override
    public User saveUserFavBook(Favorite favorite, String email) throws UserNotFoundException {
    	 User user = userBookRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserNotFoundException();
        }
       
        if(user.getFavBookList() == null)
        {
            user.setFavBookList(Arrays.asList(favorite));
        }
        else {
            List<Favorite> favorites = user.getFavBookList();
            favorites.add(favorite);
            user.setFavBookList(favorites);
        }
        return userBookRepository.save(user);
    }

    @Override
    public User deleteUserFavBookFromList(String email, String favUrl) throws  BookNotFoundException {
        boolean favUrlIsPresent = false;
        System.out.println(email);
        User user = userBookRepository.findById(email).get();
        List<Favorite> favorites = user.getFavBookList();
        favUrlIsPresent = favorites.removeIf(x->x.getFavUrl().equals(favUrl));
        if(!favUrlIsPresent)
        {
            throw new BookNotFoundException();
        }
        user.setFavBookList(favorites);
        return userBookRepository.save(user);
    }

    @Override
    public List<Favorite> getAllUserFavBooks(String email) throws UserNotFoundException {
        if(userBookRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userBookRepository.findById(email).get().getFavBookList();
    }

   
}
