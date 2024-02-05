package com.example.productservice.Controller;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Category;
import com.example.productservice.Repository.CategoryRepository;

import com.example.productservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{

    @Autowired
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategoris(){
        return this.categoryService.getCategories();
    }

    @PostMapping("")
    public Category createCategory(@RequestBody Category category)
    {
        return this.categoryService.createCategory(category);
    }

    @GetMapping("{category_id}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable (value = "category_id")Long categoryID) throws ResourceNotFoundException {
        return this.categoryService.getCategoryByID(categoryID);
    }
}
