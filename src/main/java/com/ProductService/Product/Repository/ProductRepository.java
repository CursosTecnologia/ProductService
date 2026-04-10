package com.ProductService.Product.Repository;

import com.ProductService.Product.Entity.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<@NonNull ProductEntity,@NonNull Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sh_venta.tb_product (name_product, description_product, price_unid_product)" +
    "VALUES (:name_product, :description_product, :price_unid_product);",nativeQuery = true)
    void addProduct(@Param("name_product")String nameProduct,
                    @Param("description_product")String descriptionProduct,
                    @Param("price_unid_product")Double priceUnidProduct);


    List<ProductEntity> findAllByNameProduct(String nameProduct);

//    @Query(value = """
//            Select * from sh_venta.tb_product
//            Where id_Product = : id_Product;""",nativeQuery = true)
//    List<ProductEntity> findAllByIdProduct(@Param("id_Product") Integer idProduct);

    List<ProductEntity> findAllByIdProductAndNameProduct(Integer idProduct, String nameProduct);

    @Modifying(clearAutomatically = true)
    @Query(value = """
                 UPDATE sh_venta.tb_product
                 SET name_product=:name_product,
                 description_product=:description_product, price_unid_product=:price_unid_product
                 WHERE id_product=:id_product""",nativeQuery = true)
    int updateProduct(@Param("id_product") Integer idProduct,
                     @Param("name_product") String nameProduct,
                     @Param("description_product") String descriptionProduct,
                     @Param("price_unid_product") Double priceUnidProduct);

}
