package com.blogpost.restfullapi.Payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {
    private String username;
    private String name;
    private String email;
    private String password;

}
