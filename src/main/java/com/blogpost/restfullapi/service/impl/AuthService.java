package com.blogpost.restfullapi.service.impl;

import com.blogpost.restfullapi.Payload.LoginDto;
import com.blogpost.restfullapi.Payload.RegisterDto;
import com.blogpost.restfullapi.entity.Role;
import com.blogpost.restfullapi.entity.User;
import com.blogpost.restfullapi.exception.BlogAPIException;
import com.blogpost.restfullapi.repository.RoleRepository;
import com.blogpost.restfullapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.HashSet;

@Service
public class AuthService implements com.blogpost.restfullapi.service.AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        //use authenticate method from security
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));
      //store in holder

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User login successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        //if exist or not
        Boolean existsByEmail =userRepository.existsByEmail(registerDto.getEmail());
        if(existsByEmail){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"this user already exist");
        }
        Boolean existsByUsername =userRepository.existsByUsername(registerDto.getUsername());
        if(existsByUsername){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"this user already exist");
        }
        //create user
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());

        Set<Role> roles =new HashSet<>();

        Role role = roleRepository.findByname("user").get();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        return "user register successfully";
    }
}
