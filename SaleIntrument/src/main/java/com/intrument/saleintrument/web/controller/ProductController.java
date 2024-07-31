package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.dto.request.ProductRequest;
import com.intrument.saleintrument.web.models.Product;
import com.intrument.saleintrument.web.repository.CategoryRepository;
import com.intrument.saleintrument.web.service.ProductService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<String> uploadProduct(
            @RequestBody ProductRequest request) {

        try {
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStock_quantity(request.getStock_quantity());
            product.setCategory(categoryRepository.findByName(request.getCategoryName())); // Assuming you have a method to fetch Category by ID
            product.setImage(request.getImage());

            productService.saveProduct(product);

            return ResponseEntity.ok("Product uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload product");
        }
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request){


        try {
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStock_quantity(request.getStock_quantity());
            product.setCategory(categoryRepository.findByName(request.getCategoryName()));
            product.setImage(request.getImage());

            productService.updateProduct(id, product);

            return ResponseEntity.ok("Product update successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
        }
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getAllProductByCategory(@PathVariable String categoryName){
        return productService.getAllProductByCategory(categoryName);
    }




}
