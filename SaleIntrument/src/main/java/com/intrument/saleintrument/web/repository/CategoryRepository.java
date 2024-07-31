package com.intrument.saleintrument.web.repository;

import com.intrument.saleintrument.web.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
