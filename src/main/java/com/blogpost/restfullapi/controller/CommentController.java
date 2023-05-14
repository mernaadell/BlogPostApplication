package com.blogpost.restfullapi.controller;

import com.blogpost.restfullapi.Payload.CommentDto;
import com.blogpost.restfullapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{post_id}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long post_id,
            @RequestBody  CommentDto commentDto
    ){
     return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("posts/{post_id}/comments")
    public List<CommentDto> getCommentsByPostId(
            @PathVariable Long post_id
    ){
        return  commentService.getCommentsByPostId(post_id);
    }
    @GetMapping("posts/{post_id}/comments/{comment_id}")
    public CommentDto getCommentsByPostId(
            @PathVariable Long post_id,
            @PathVariable Long comment_id
            ){
        return  commentService.getCommentByPostIdAndCommentId(post_id,comment_id);
    }
    @DeleteMapping("posts/{post_id}/comments/{comment_id}")
    public String DeleteComment(
            @PathVariable Long post_id,
            @PathVariable Long comment_id
    ){
        return  commentService.deleteComment(post_id,comment_id);
    }
}
