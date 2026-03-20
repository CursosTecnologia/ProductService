package com.ProductService.Product.Repository;

import com.ProductService.Product.Entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sh_venta.tb_product (name_product, description_product, price_unid_product)" +
    "VALUES (:name_product, :description_product, :price_unid_product);",nativeQuery = true)
    void addProduct(@Param("name_product")String nameProduct,
                    @Param("description_product")String descriptionProduct,
                    @Param("price_unid_product")Double priceUnidProduct);


    List<ProductEntity> findAllByNameProduct(String nameProduct);

    List<ProductEntity> findAllById(String idProduct);
}
