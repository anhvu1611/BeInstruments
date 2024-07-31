package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Category;
import com.intrument.saleintrument.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        category.setCreatedDate(LocalDate.now());
        category.setUpdatedDate(LocalDate.now());
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public boolean deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null)
            return false;
        categoryRepository.deleteById(id);
        return true;
    }

    public Category updateCategory(Long id, Category category){
        Category category1 = categoryRepository.findById(id).orElse(null);
        assert category1 != null;
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        return categoryRepository.save(category1);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

}
