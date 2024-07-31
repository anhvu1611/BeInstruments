package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Order_Details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<Order_Details, Long> {
    Order_Details findOrder_DetailsByOrderId(Long orderId);
}
