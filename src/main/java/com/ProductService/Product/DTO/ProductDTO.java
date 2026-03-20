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
    private String nameProduct;
    private String descriptionProduct;
    private Double priceUnidProduct;


}
