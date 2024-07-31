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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private String id;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Double amount;
    private LocalDate payment_date;
    private String payment_method;
    private String status;
}
