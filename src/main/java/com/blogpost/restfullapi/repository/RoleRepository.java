package com.blogpost.restfullapi.repository;

import com.blogpost.restfullapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByname(String name);
}
