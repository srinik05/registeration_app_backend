package com.home.prj.repository;


import com.home.prj.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

//Where is the implementation?
//Spring Boot generates it at runtime.

//What happens internally?
//When Spring starts, it sees:

//        extends MongoRepository<User, String>

//and creates an implementation similar to:

//class UserRepositoryImpl implements UserRepository {
//}
//You never write it yourself.
//This is one of Spring Data's biggest strengths.