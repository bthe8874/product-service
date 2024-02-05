package com.example.productservice.Service.Impl;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Category;
import com.example.productservice.Repository.CategoryRepository;
import com.example.productservice.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private  final CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        try{
            return categoryRepository.save(category);
        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            logger.error("Already existing category.");
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryByID(Long categoryID) throws ResourceNotFoundException {
        try{
            Category category=categoryRepository.findById(categoryID).orElseThrow(
                    ()-> new ResourceNotFoundException("Category not found with this ID"+ categoryID));
            return ResponseEntity.ok(category);
        }catch (ResourceNotFoundException resourceNotFoundException)
        {
            logger.error("Category not found with given ID.",resourceNotFoundException);
            throw resourceNotFoundException;
        }


    }
}
