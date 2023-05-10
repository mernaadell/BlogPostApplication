package com.blogpost.restfullapi.repository;

import com.blogpost.restfullapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//post repository for post entity
public interface PostRepository extends JpaRepository<Post,Long> { //generic class //get all crud for post entity


}
