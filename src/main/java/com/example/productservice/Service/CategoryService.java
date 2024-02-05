package com.example.productservice.Service;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService
{
    List<Category> getCategories();
    Category createCategory(Category category);
    ResponseEntity<Category> getCategoryByID(Long categoryID) throws ResourceNotFoundException;






}
