package com.cloudmart.repository;

import com.cloudmart.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {



    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.name = ?1")
    void deleteProductByName(String productName);

    @Query("SELECT p FROM Product p WHERE p.id BETWEEN :minId AND :maxId")
    List<Product> findProductsInRange(@Param("minId") int minId, @Param("maxId") int maxId);


    @Query("SELECT p FROM Product p WHERE LOWER(p.category) LIKE %:data% OR LOWER(p.name) LIKE %:data%")
    List<Product> searchProducts(@Param("data") String data);


    @Query("SELECT p FROM Product p WHERE p.price>=:min AND p.price<=:max")
    List<Product> priceFilter(@Param("min") int min,@Param("max") int max);




      }



