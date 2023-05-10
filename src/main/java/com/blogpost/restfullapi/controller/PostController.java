package com.blogpost.restfullapi.controller;

import com.blogpost.restfullapi.Payload.PostDto;
import com.blogpost.restfullapi.Payload.PostResponse;
import com.blogpost.restfullapi.service.PostService;
import com.blogpost.restfullapi.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    //inject interface to achieve loose coupling
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable(name = "id") Long postId) {
        return postService.getPost(postId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {

        postService.deletePost(id);

        return "Post Deleted Successfully";
    }

}