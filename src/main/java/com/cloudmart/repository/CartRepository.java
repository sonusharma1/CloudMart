package com.cloudmart.repository;

import com.cloudmart.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Integer> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.id = :cartId")
    void deleteByCartId(@Param("cartId") int cartId);

}
