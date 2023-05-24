package com.blogpost.restfullapi.repository;

import com.blogpost.restfullapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository< Category,Long> {
}
