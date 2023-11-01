package com.cloudmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cartitems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double totalPrice;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;
}
