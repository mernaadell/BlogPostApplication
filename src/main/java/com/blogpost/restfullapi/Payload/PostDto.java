package com.blogpost.restfullapi.Payload;

import com.blogpost.restfullapi.entity.Comment;
import lombok.Data;
import java.util.*;
@Data

public class PostDto {

    private Long id;

    private String title;

    private String description;

    private String content;

    private List<Comment> comments;
}
