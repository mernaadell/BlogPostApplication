package com.blogpost.restfullapi.repository;


import com.blogpost.restfullapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
