package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<Cart_Item, Long> {
    Cart_Item findByCartId(Long cartId);
}
