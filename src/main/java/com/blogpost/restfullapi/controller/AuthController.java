package com.blogpost.restfullapi.controller;

import com.blogpost.restfullapi.Payload.JWTAuthResponse;
import com.blogpost.restfullapi.Payload.LoginDto;
import com.blogpost.restfullapi.Payload.RegisterDto;
import com.blogpost.restfullapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})

    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setTokenType("bearer");

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<JWTAuthResponse> register(@RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setTokenType("bearer");

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.CREATED);
    }


}
