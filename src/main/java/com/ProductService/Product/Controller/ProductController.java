package com.ProductService.Product.Controller;

import com.ProductService.Product.Entity.ProductEntity;
import com.ProductService.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping
    public List<ProductEntity> listProduct(){
        return productRepository.findAll();
    }
}