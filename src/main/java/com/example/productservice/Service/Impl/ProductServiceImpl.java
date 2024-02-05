package com.example.productservice.Service.Impl;

import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Model.Product;
import com.example.productservice.Model.ProductStatus;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Service.ProductService;
import org.hibernate.procedure.ProcedureOutputs;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


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
    public Product createProduct(Product product) {
        try{
            return this.productRepository.save(product);
        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            logger.error("Product already exists.");
            throw dataIntegrityViolationException;  }
    }

    @Override
    public ResponseEntity<Product> getProductById(Long proudctID) throws ResourceNotFoundException {

        try{
            Product product = productRepository.findById(proudctID).orElseThrow(() ->
                    new ResourceNotFoundException("Product not found with this ID :" + proudctID));

            return ResponseEntity.ok(product);
        }catch (ResourceNotFoundException resourceNotFoundException)
        {
            logger.error("Error while fetching products ID.",resourceNotFoundException);
            throw resourceNotFoundException;
        }


    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long products_id) throws ResourceNotFoundException {
        try{
            Product product = productRepository.findById(products_id).
                    orElseThrow(() ->
                            new ResourceNotFoundException("Product not found with given ID : " + products_id));

            productRepository.delete(product);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted" ,  Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch (ResourceNotFoundException resourceNotFoundException){
            logger.error("Error while deleting the product ID.", resourceNotFoundException);
            throw  resourceNotFoundException;
        }


    }
    @Override
    public ResponseEntity<Product> updateProduct(Long products_id,Product productDetails) throws ResourceNotFoundException
    {

        try{
            Product product = productRepository.findById(products_id).orElseThrow(() ->
                    new ResourceNotFoundException("Product not found with given ID : " + products_id));

            modelMapper.map(productDetails,product);

            product.setProductName(productDetails.getProductName());
            product.setProductPrice(productDetails.getProductPrice());
            product.setProductDetail(product.getProductDetail());
            final Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        }catch(ResourceNotFoundException resourceNotFoundException){
            logger.error("Error while updating the porduct ID.", resourceNotFoundException);
            throw resourceNotFoundException;
        }
    }

    @Override
    public ResponseEntity<Product> updateProductStock(Long products_id, Product productdetails) throws ResourceNotFoundException
    {
        try{
            Product product = productRepository.findById(products_id).orElseThrow(() ->
                    new ResourceNotFoundException("Product with this ID not exist."));

            product.setStockAvailable(productdetails.getStockAvailable()+product.getStockAvailable());
            if(product.getStockAvailable() > 0){
                product.setStatus(ProductStatus.AVAIALABLE);
            }
            final Product updatedproduct =productRepository.save(product);
            return ResponseEntity.ok(updatedproduct);
        }catch (ResourceNotFoundException resourceNotFoundException){
            logger.error("Error while updating the stock of product ID"+products_id, resourceNotFoundException);
            throw resourceNotFoundException;
        }
    }


}
