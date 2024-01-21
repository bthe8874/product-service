package com.example.productservice.Service.Impl;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Product;
import com.example.productservice.Model.ProductStatus;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Service.ProductService;
import org.hibernate.procedure.ProcedureOutputs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    private  final ProductRepository productRepository;

    @Autowired
    private  final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository , ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper= modelMapper;
    }

    @Override
    public List<Product> getAllProducts()
    {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product)
    {

        return this.productRepository.save(product);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long proudctID)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(proudctID).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with this ID :" + proudctID));

        return ResponseEntity.ok(product);

    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long products_id) throws ResourceNotFoundException {
        Product product = productRepository.findById(products_id).
                orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with given ID : " + products_id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted" ,  Boolean.TRUE);
        return ResponseEntity.ok(response);


    }
    @Override
    public ResponseEntity<Product> updateProduct(
            Long products_id,
            Product productDetails) throws ResourceNotFoundException
    {

        Product product = productRepository.findById(products_id).
                orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with given ID : " + products_id));

        //modelMapper.map(productDetails,product);

        product.setProductName(productDetails.getProductName());
        product.setProductPrice(productDetails.getProductPrice());
        product.setProductDetail(product.getProductDetail());

        final Product updatedProduct = productRepository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }

    @Override
    public ResponseEntity<Product> updateProductStock(Long products_id, Product productdetails) throws ResourceNotFoundException
    {
        Product product = productRepository.findById(products_id).orElseThrow(
                () -> new ResourceNotFoundException("Product with this ID not exist.")
        );

        product.setStockAvailable(productdetails.getStockAvailable()
                +
                product.getStockAvailable());

        if(product.getStockAvailable() > 0)
        {
            product.setStatus(ProductStatus.AVAIALABLE);
        }

        final Product updatedproduct =productRepository.save(product);

        return ResponseEntity.ok(updatedproduct);






    }


}
