package com.ProductService.Product.Service;

import com.ProductService.Product.DTO.ProductDTO;
import com.ProductService.Product.Entity.ProductEntity;
import com.ProductService.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void insertProduct(ProductDTO productDTO){
        ProductEntity productEntity= mapperProduct(productDTO);
        productRepository.addProduct(productEntity.getNameProduct(),productEntity.getDescriptionProduct(),productEntity.getPriceUnidProduct());
    }

    private ProductEntity mapperProduct(ProductDTO productDTO){
        ProductEntity productEntity= new ProductEntity();

        productEntity.setNameProduct(productDTO.getNameProduct());
        productEntity.setDescriptionProduct(productDTO.getDescriptionProduct());
        productEntity.setPriceUnidProduct(productDTO.getPriceUnidProduct());

        return productEntity;
    }

}
