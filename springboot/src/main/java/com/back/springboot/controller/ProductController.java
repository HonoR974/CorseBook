package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.model.Product;
import com.back.springboot.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    

    //get all 
    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    //create 
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product)
    {
        System.out.println("\n products " + product.toString());
        return productRepository.save(product);
    }

    //get by ID 
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id)
    {
        Product product =productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
  
        return ResponseEntity.ok(product);
    }
    
    //update by ID 
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetail)
    {
        Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 
        product.setName(productDetail.getName());
        product.setType(productDetail.getType());

       Product productUpdated =  productRepository.save(product);
   
        return ResponseEntity.ok(productUpdated);
    }


}
