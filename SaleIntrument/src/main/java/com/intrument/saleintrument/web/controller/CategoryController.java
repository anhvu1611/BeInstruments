package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.dto.response.CategoryResponse;
import com.intrument.saleintrument.web.models.Category;
import com.intrument.saleintrument.web.repository.ProductRepository;
import com.intrument.saleintrument.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable  Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategory(){
        List<Category> categories = categoryService.getAllCategory();
        return categories.stream().map(category -> new CategoryResponse(category.getId(), category.getName(), category.getDescription(), productRepository.countByCategory_Name(category.getName()))).toList();
    }

}
