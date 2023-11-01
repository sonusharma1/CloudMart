package com.cloudmart.repository;


import com.cloudmart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findFirstByNameAndContactNumber(String name,String contactNumber);

    Customer findByEmailAddress(String emailAddress);





}
