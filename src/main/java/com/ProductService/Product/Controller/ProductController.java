package com.ProductService.Product.Controller;

import com.ProductService.Product.DTO.ProductDTO;
import com.ProductService.Product.Entity.ProductEntity;
import com.ProductService.Product.Repository.ProductRepository;
import com.ProductService.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @GetMapping
    public List<ProductEntity> listProduct(){
        return productRepository.findAll();
    }

    @PostMapping
    public void insertProduct(@PathVariable String nameProduct,@PathVariable String descriptionProduct,@PathVariable Double priceUnidProduct){
        ProductDTO productDTO = new ProductDTO(nameProduct,descriptionProduct,priceUnidProduct);
        productService.insertProduct(productDTO);
    }
}
