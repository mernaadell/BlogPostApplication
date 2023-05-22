package com.blogpost.restfullapi.service;

import com.blogpost.restfullapi.Payload.LoginDto;
import com.blogpost.restfullapi.Payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
