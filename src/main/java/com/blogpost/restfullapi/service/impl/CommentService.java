package com.blogpost.restfullapi.service.impl;

import com.blogpost.restfullapi.Payload.CommentDto;
import com.blogpost.restfullapi.entity.Comment;
import com.blogpost.restfullapi.entity.Post;
import com.blogpost.restfullapi.exception.BlogAPIException;
import com.blogpost.restfullapi.exception.ResourceNotFoundException;
import com.blogpost.restfullapi.repository.CommentRepository;
import com.blogpost.restfullapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements com.blogpost.restfullapi.service.CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(Long post_id, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        Post post = postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("post", "post", post_id));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(Long postId, Long comment_id) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post", postId));
        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("comment", "comment", comment_id));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        return mapToDto(comment);
    }

    @Override
    public String deleteComment(Long postId, Long comment_id) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post", postId));
        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("comment", "comment", comment_id));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentRepository.delete(comment);
        return "Deleted";
    }

    CommentDto mapToDto(Comment comment) {

        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());

        return commentDto;

    }

    Comment mapToComment(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());

        return comment;

    }
}
