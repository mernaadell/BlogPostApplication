package com.blogpost.restfullapi.service;

import com.blogpost.restfullapi.Payload.CommentDto;
import java.util.*;
public interface CommentService {

    CommentDto createComment(Long post_id,CommentDto commentDto);

    List<CommentDto>  getCommentsByPostId(Long postId);
    CommentDto  getCommentByPostIdAndCommentId(Long postId,Long comment_id);
    String deleteComment(Long postId,Long comment_id);
}
