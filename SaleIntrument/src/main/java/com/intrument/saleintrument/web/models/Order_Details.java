package com.intrument.saleintrument.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "order_details")
public class Order_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @ManyToMany
//    @JoinTable(
//            name = "order_details_products",
//            joinColumns = @JoinColumn(name = "order_details_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    @OneToOne
//    @JoinColumn(name = "product_id")
//    private Product products;
//
//    private Integer quantity;
//    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
