package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Order;
import com.intrument.saleintrument.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Integer countOrderByUser(User user);
    List<Order> findByUser(User user);

}
