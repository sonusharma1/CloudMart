package com.cloudmart.repository;


import com.cloudmart.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository <CustomerOrder,Integer>{
}
