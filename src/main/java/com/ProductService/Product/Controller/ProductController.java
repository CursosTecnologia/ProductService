package com.ProductService.Product.Controller;

import com.ProductService.Product.DTO.ProductDTO;
import com.ProductService.Product.Entity.ProductEntity;
import com.ProductService.Product.Repository.ProductRepository;
import com.ProductService.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @GetMapping("/listarTodo")
    public List<ProductEntity> listProduct(){
        return productRepository.findAll();
    }

    @PostMapping
    public void insertProduct(@RequestParam String nameProduct, @RequestParam String descriptionProduct, @RequestParam Double priceUnidProduct){
        ProductDTO productDTO = new ProductDTO(nameProduct,descriptionProduct,priceUnidProduct);
        productService.insertProduct(productDTO);
    }

    @GetMapping("/listar")
    public List<ProductDTO> listarProductoIdName(@RequestParam String name){
        return productService.findAllByNameProduct(name);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Integer> updateProduct(@RequestBody ProductDTO productDTO) {
        // 1. Llamamos al servicio para ejecutar la lógica de actualización
        int rowsAffected = productService.updateProduct(productDTO);

        // 2. Si el servicio no lanzó una excepción y devolvió filas afectadas:
        return ResponseEntity.ok(rowsAffected);
    }
}
