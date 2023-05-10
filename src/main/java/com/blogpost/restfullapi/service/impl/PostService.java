package com.blogpost.restfullapi.service.impl;

import com.blogpost.restfullapi.Payload.PostDto;
import com.blogpost.restfullapi.Payload.PostResponse;
import com.blogpost.restfullapi.entity.Post;
import com.blogpost.restfullapi.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service //will inject this class in other
public class PostService implements com.blogpost.restfullapi.service.PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(
                pageNo,
                pageSize,
                sort
        );

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listPosts = posts.getContent();
        System.out.println(posts);
        List<PostDto> content = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getSize());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new com.springboot.blog.exception.ResourceNotFoundException("post", "post", postId));
        PostDto postResponse = mapToDTO(post);
        return postResponse;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new com.springboot.blog.exception.ResourceNotFoundException("post", "post", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post post1 = postRepository.save(post);
        PostDto postResponse = mapToDTO(post1);
        return postResponse;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new com.springboot.blog.exception.ResourceNotFoundException("post", "post", id));
        postRepository.delete(post);

    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

}