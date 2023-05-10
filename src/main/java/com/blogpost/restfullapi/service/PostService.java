package com.blogpost.restfullapi.service;

import com.blogpost.restfullapi.Payload.PostDto;
import com.blogpost.restfullapi.Payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize ,String sortBy,String sortDir);

    PostDto getPost(Long postId);

    PostDto updatePost(PostDto postDto,Long id);

    void deletePost(Long id);
}
