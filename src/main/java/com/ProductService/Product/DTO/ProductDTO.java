package com.ProductService.Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private Double priceUnidProduct;

    public ProductDTO(String nameProduct, String descriptionProduct, Double priceUnidProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceUnidProduct = priceUnidProduct;
    }
}
