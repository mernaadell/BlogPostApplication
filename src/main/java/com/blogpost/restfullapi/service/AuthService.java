package com.blogpost.restfullapi.service;

import com.blogpost.restfullapi.Payload.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}
