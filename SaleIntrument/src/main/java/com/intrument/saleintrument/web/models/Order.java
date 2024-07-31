package com.intrument.saleintrument.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractModel{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate orderDate;
    private String status;
    private Double total;
    private String address;


//    @OneToOne(mappedBy = "order")
//    private Payment payment;
}
