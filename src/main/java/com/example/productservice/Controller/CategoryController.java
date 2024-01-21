package com.example.productservice.Controller;

import com.example.productservice.Model.Category;
import com.example.productservice.Repository.CategoryRepo;

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
    private final CategoryRepo categoryRepo;

    CategoryController(CategoryRepo categoryRepo)
    {
        this.categoryRepo = categoryRepo;
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody Category category)
    {
        try{
            Category newCategory = categoryRepo.save(category);
            return ResponseEntity.ok(newCategory);
        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            return ResponseEntity.badRequest().body("Already existing category.");
        }


    }

    @GetMapping("")
    public List<Category> getCategory()
    {
        return categoryRepo.findAll();
    }






}
