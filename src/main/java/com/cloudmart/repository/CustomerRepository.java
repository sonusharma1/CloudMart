package com.cloudmart.repository;


import com.cloudmart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {



    Customer findByEmailAddress(String emailAddress);





}
