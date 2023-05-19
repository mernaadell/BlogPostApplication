package com.blogpost.restfullapi.Payload;

import com.blogpost.restfullapi.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.*;

@Data

public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 10, message = "Post Should have atleast 2 char an max 10 char")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "description should have atlease 10 char")
    private String description;
    @NotEmpty
    private String content;

    private List<Comment> comments;
}
