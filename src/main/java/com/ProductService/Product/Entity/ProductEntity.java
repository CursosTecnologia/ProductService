package com.ProductService.Product.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_product", schema = "sh_venta")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "name_product")
    private String nameProduct;
    @Column(name = "description_product")
    private String descriptionProduct;
    @Column(name = "price_unid_product")
    private double priceUnidProduct;

}
