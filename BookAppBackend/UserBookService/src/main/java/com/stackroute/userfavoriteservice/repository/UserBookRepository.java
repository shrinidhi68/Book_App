package com.stackroute.userfavoriteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userfavoriteservice.domain.User;

public interface UserBookRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
}
