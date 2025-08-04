package com.stackroute.userfavoriteservice.repository;

import com.stackroute.userfavoriteservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBookRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
