package com.blogpost.restfullapi.service.impl;

import com.blogpost.restfullapi.Payload.CategoryDto;
import com.blogpost.restfullapi.Payload.CommentDto;
import com.blogpost.restfullapi.entity.Category;
import com.blogpost.restfullapi.entity.Comment;
import com.blogpost.restfullapi.exception.ResourceNotFoundException;
import com.blogpost.restfullapi.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements com.blogpost.restfullapi.service.CategoryService {

    private CategoryRepository categoryRepository;
    ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {

        Category cat = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category","category",id));
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category cat = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category","category",id));
        cat.setName(categoryDto.getName());
        cat.setDescription(categoryDto.getDescription());

       Category cat2 = categoryRepository.save(cat);

        return modelMapper.map(cat2,CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long id) {
        Category cat = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category","category",id));
        categoryRepository.delete(cat);
        return "deleted successfully";
    }

}
