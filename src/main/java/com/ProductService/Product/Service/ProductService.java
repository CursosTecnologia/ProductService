package com.ProductService.Product.Service;

import com.ProductService.Product.DTO.ProductDTO;
import com.ProductService.Product.Entity.ProductEntity;
import com.ProductService.Product.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void insertProduct(ProductDTO productDTO){
        ProductEntity productEntity= mapperProduct(productDTO);
        productRepository.addProduct(productEntity.getNameProduct(),productEntity.getDescriptionProduct(),productEntity.getPriceUnidProduct());
    }

    public List<ProductDTO> findAllByNameProduct(String nameProduct){
        List<ProductDTO> productDTOList = new ArrayList<>();

        List<ProductEntity> listaEntity = productRepository.findAllByNameProduct(nameProduct);
        for (ProductEntity productEntity: listaEntity){
            ProductDTO productDTO = mapperProductDto(productEntity);
            productDTOList.add(productDTO);
        }
        return productDTOList;

    }

    @Transactional
    public int updateProduct(ProductDTO productDTO) {
        // 1. Ejecutamos la actualización usando los datos del DTO
        // El repositorio devolverá 1 si tuvo éxito, o 0 si el ID no existe
        int rowsAffected = productRepository.updateProduct(
                productDTO.getIdProduct(),
                productDTO.getNameProduct(),
                productDTO.getDescriptionProduct(),
                productDTO.getPriceUnidProduct()
        );

        // 2. Validación de éxito
        if (rowsAffected == 0) {
            // Es buena práctica lanzar una excepción si el recurso no existe
            throw new EntityNotFoundException("No se pudo actualizar: El producto con ID "
                    + productDTO.getIdProduct() + " no fue encontrado.");
        }

        // 3. Retornamos el número de filas (o podrías retornar un boolean/void según prefieras)
        return rowsAffected;
    }

    private ProductEntity mapperProduct(ProductDTO productDTO){
        ProductEntity productEntity= new ProductEntity();

        productEntity.setIdProduct(productDTO.getIdProduct());
        productEntity.setNameProduct(productDTO.getNameProduct());
        productEntity.setDescriptionProduct(productDTO.getDescriptionProduct());
        productEntity.setPriceUnidProduct(productDTO.getPriceUnidProduct());

        return productEntity;

    }

    private ProductDTO mapperProductDto(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdProduct(productEntity.getIdProduct());
        productDTO.setNameProduct(productEntity.getNameProduct());
        productDTO.setDescriptionProduct(productEntity.getDescriptionProduct());
        productDTO.setPriceUnidProduct(productEntity.getPriceUnidProduct());
        return productDTO;
    }

}
