package com.blogpost.restfullapi.service;

import com.blogpost.restfullapi.Payload.CategoryDto;
import com.blogpost.restfullapi.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getCategories();

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto getCategory(Long id);

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    public String deleteCategory(Long id);
}
