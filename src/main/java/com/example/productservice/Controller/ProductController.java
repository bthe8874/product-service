package com.example.productservice.Controller;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Category;
import com.example.productservice.Model.Product;
import com.example.productservice.Repository.CategoryRepo;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController
{

    @Autowired
    private final ProductService productService;


    ProductController( ProductService productService) {
        this.productService = productService;
    }


    //get product
    @GetMapping("")
    public List<Product> getAllProducts()
    {
        return this.productService.getAllProducts();
    }

    //post product
    @PostMapping("")
    public Product createProduct(@RequestBody Product product)
    {
        return this.productService.createProduct(product);
    }



    //get product by ID
    @GetMapping("/{products_id}")
    public ResponseEntity<Product> getProductbyID(@PathVariable(value = "products_id") Long proudctID) throws ResourceNotFoundException {
        return productService.getProductById(proudctID);

    }

    //delete product
    @DeleteMapping("/{products_id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long products_id) throws ResourceNotFoundException {

        return productService.deleteProduct(products_id);
    }
    //update a product
    @Transactional
    @PutMapping("/{products_id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable (value = "products_id") Long products_id,
            @RequestBody Product productDetails) throws ResourceNotFoundException
    {


        return productService.updateProduct(products_id,productDetails);
    }

    @Transactional
    @PutMapping("/{products_id")
    public ResponseEntity<Product> updateStockAvaiable(
            @PathVariable (value = "products_id") Long products_id,
            @RequestBody Product productDetails) throws ResourceNotFoundException
    {
        return productService.updateProductStock(products_id,productDetails);
    }




}
