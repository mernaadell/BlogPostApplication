package com.blogpost.restfullapi.repository;

import com.blogpost.restfullapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //add custom queries
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username,String email);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
