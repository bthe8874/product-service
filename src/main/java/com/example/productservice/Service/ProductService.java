package com.example.productservice.Service;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService
{
    List<Product> getAllProducts();
    Product createProduct(Product product);
    ResponseEntity<Product>  getProductById(Long products_id) throws ResourceNotFoundException;
    ResponseEntity <Map<String, Boolean>> deleteProduct(Long products_id) throws ResourceNotFoundException;

    ResponseEntity <Product> updateProduct (Long products_id , Product productDetails) throws ResourceNotFoundException;

}
