package com.cloudmart.repository;


import com.cloudmart.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRpository extends JpaRepository<Orderitem,Integer> {
}
