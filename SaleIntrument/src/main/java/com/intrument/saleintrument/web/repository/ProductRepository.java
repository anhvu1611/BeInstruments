package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Name(String categoryName);
    Integer countByCategory_Name(String categoryName);
}
