package com.cloudmart.repository;

import com.cloudmart.entity.Cartitems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartitemsRepository extends JpaRepository<Cartitems, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Cartitems c WHERE c.id = :itemId")
    void deleteCartItemById(@Param("itemId") Long itemId);
}

