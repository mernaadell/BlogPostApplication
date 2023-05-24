package com.blogpost.restfullapi.controller;

import com.blogpost.restfullapi.Payload.CategoryDto;
import com.blogpost.restfullapi.entity.Category;
import com.blogpost.restfullapi.service.CategoryService;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getCategories(){
       return categoryService.getCategories();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> CreateCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto category = categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategory(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id){

        CategoryDto category = categoryService.updateCategory(categoryDto,id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
