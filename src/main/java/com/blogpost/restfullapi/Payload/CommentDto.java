package com.blogpost.restfullapi.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    @NotEmpty
    private String name;
    @Email(message = "email should have valid email address")
    @NotEmpty
    private String email;
    @NotEmpty
    private String body;

}
