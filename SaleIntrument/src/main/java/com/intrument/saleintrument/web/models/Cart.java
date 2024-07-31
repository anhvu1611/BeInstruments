package com.intrument.saleintrument.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart extends AbstractModel{
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<Cart_Item> cartItems = new HashSet<>();

}
