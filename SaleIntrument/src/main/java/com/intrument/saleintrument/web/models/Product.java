package com.intrument.saleintrument.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AbstractModel{
    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToMany(mappedBy = "products")
//    private List<Order_Details> orderDetails;

}
