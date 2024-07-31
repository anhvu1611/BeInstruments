package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Cart;
import com.intrument.saleintrument.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
    Cart findByUser(User user);
}
