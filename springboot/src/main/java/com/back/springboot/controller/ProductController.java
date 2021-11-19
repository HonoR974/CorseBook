package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.model.Product;
import com.back.springboot.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product)
    {
        System.out.println("\n products " + product.toString());
        return productRepository.save(product);
    }
    
}
