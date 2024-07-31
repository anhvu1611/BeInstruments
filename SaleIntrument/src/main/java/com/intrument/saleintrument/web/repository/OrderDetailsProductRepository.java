package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.OrderDetailsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsProductRepository extends JpaRepository<OrderDetailsProduct, Long> {
    List<OrderDetailsProduct> findByOrderDetailsId(Long orderDetailsId);
}
