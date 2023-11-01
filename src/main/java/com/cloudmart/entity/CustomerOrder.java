package com.cloudmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double finalTotalPrice;
    private LocalDate localDate;
    @JsonIgnore
    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    List<Orderitem> orderitemList;

}
