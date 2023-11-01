package com.cloudmart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=31;
    private String name;
    private String  surName;
    private String  contactNumber;
    private String  emailAdress;
    private String  adress;



    @OneToOne(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrderList;
}
