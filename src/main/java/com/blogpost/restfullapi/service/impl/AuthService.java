package com.blogpost.restfullapi.service.impl;

import com.blogpost.restfullapi.Payload.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements com.blogpost.restfullapi.service.AuthService {
    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private AuthenticationManager authenticationManager;
    @Override
    public String login(LoginDto loginDto) {
        //use authenticate method from security
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));
      //store in holder

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User login successfully";
    }
}
